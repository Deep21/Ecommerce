package dw.fdb.com.fdbapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devspark.appmsg.AppMsg;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.paypal.android.sdk.payments.ShippingAddress;
import com.viewpagerindicator.CirclePageIndicator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.adapter.ViewPagerProductImageAdapter;
import dw.fdb.com.fdbapp.model.CartException;
import dw.fdb.com.fdbapp.model.CartProduct;
import dw.fdb.com.fdbapp.model.Image;
import dw.fdb.com.fdbapp.model.Product;
import dw.fdb.com.fdbapp.model.ProductModel;
import dw.fdb.com.fdbapp.model.Token;
import dw.fdb.com.fdbapp.request.CartAddProductPostRequest;
import dw.fdb.com.fdbapp.request.ProductDetailRequest;
import dw.fdb.com.fdbapp.views.SmartViewPager;

public class ProductDetailScrollFragment extends BaseFragment {
    public static final String TAG = "ProductDetailScrollFragment";
    private static final String CONFIG_CLIENT_ID = "Ae-XPhCaF0M6CXDhtrWFQI4h6v33aue4k56LJM5f1EoxAOaHxOO7zw1v5PR7";
    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_CODE_PROFILE_SHARING = 3;
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
            .merchantName("Hipster Store")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
    @InjectView(R.id.libelle_produit)
    TextView libelle_produit;
    @InjectView(R.id.prix)
    TextView prix;
    @InjectView(R.id.description)
    TextView description;
    @InjectView(R.id.stock)
    TextView stock;
    @InjectView(R.id.description_short)
    TextView description_short;
    @InjectView(R.id.smart_view_pager)
    SmartViewPager smart_view_pager;
    @InjectView(R.id.circle_indicator)
    CirclePageIndicator circle_indicator;

    ViewPagerProductImageAdapter mAdapter;
    boolean isOnRequestSuccessFinished;
    FragmentListner fragmentListner;
    Product product;
    List<ProductImageFragment> productImageFragments;

    public static ProductDetailScrollFragment newInstance(int i) {
        Bundle b = new Bundle();
        b.putInt(ProductModel.ARGS_PRODUCT_ID, i);
        ProductDetailScrollFragment productDetailListFragment = new ProductDetailScrollFragment();
        productDetailListFragment.setArguments(b);
        return productDetailListFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentListner = (FragmentListner) activity;

    }

    public void onEventMainThread(CartException cartException) {
        if (cartException.getOut_of_stock() == 0) {
            AppMsg.makeText(getActivity(), "Votre produit est hors stock", AppMsg.STYLE_CONFIRM).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startPaypal();
        EventBus.getDefault().register(this);
        setRetainInstance(true);
        backgroundRequest();
//        Log.e(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_detail_scroll_view, container, false);
        ButterKnife.inject(this, v);
//        Log.e(TAG, "onCreateView");
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Log.e("ProductDetailScrollFragment", "onViewCreated");
        if (getProduct() != null) {
            setUpUi();
        }
        if (productImageFragments != null) {
            mAdapter.setFragmentimage(productImageFragments);
            mAdapter.notifyDataSetChanged();
            smart_view_pager.setAdapter(mAdapter);
            circle_indicator.setViewPager(smart_view_pager);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
//        Log.e("ProductDetailScrollFragment", "onStart");
    }

    @Override
    public void onStop() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();
//        Log.e("ProductDetailScrollFragment", "onStop");
    }

    public void backgroundRequest() {
        if (getArguments() != null) {
            int id_product = getArguments().getInt(ProductModel.ARGS_PRODUCT_ID);
//            Log.e("ProductDetailScrollFragment", "" + id_product);
            ProductDetailRequest productDetailRequest = new ProductDetailRequest(id_product);
            getSpiceManager().execute(productDetailRequest, new ProductRequestListner());
        }

    }

    /*
     * Add app-provided shipping address to payment
    */
    private void addAppProvidedShippingAddress(PayPalPayment paypalPayment) {
        ShippingAddress shippingAddress = new ShippingAddress()
                .recipientName("Sam Fisher")
                .line1("ruedfbdfbfdb ddfbfdbe Parisbfdbdfb")
                .city("Paris")
                .postalCode("75010")
                .countryCode("FR");
        paypalPayment.providedShippingAddress(shippingAddress);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        // Stop service when done
        Intent i = new Intent(getActivity(), PayPalService.class);
        getActivity().stopService(i);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private void setUpUi() {
        libelle_produit.setText(product.getName());
        prix.setText("" + Double.valueOf(product.getPrice()).floatValue());
        description_short.setText(Html.fromHtml(product.getDescriptionShort()));
        stock.setText("En Stock");
        description.setText(Html.fromHtml(product.getDescription()));
        List<Image> images = product.getImages();
        productImageFragments = new ArrayList<ProductImageFragment>();
        for (Image url : images) {
            productImageFragments.add(ProductImageFragment.newInstance(url.getUrlImage()));
        }
        mAdapter = new ViewPagerProductImageAdapter(getChildFragmentManager(), productImageFragments);
        smart_view_pager.setAdapter(mAdapter);
        circle_indicator.setViewPager(smart_view_pager);
    }

    private PayPalPayment getThingToBuy(String paymentIntent) {
        System.out.println("zefzef" + product.getPrice());
        return new PayPalPayment(new BigDecimal("5"), "EUR", "hipster jeans", paymentIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
        if (confirm != null) {
            System.out.println(confirm.getPayment().toJSONObject());
            System.out.println(confirm.getPayment().toString());
            System.out.println(confirm.getProofOfPayment().toJSONObject());
            System.out.println(confirm.getProofOfPayment().getPaymentId());
            if (confirm.getProofOfPayment().getTransactionId() != null) {
                System.out.println(confirm.getProofOfPayment().getTransactionId());
            }
        }
    }

    /*
     * This method shows use of optional payment details and item list.
     */
    private PayPalPayment getStuffToBuy(String paymentIntent) {
        double price = (Math.round(getProduct().getOrderprice() * 100.0) / 100.0);
        PayPalItem[] items = {new PayPalItem(getProduct().getName(), 1, new BigDecimal("" + price), "EUR", "646fgerg654")};
        BigDecimal subtotal = PayPalItem.getItemTotal(items);
        BigDecimal shipping = new BigDecimal("0.21");
        BigDecimal tax = new BigDecimal("1");
        PayPalPaymentDetails paymentDetails = new PayPalPaymentDetails(shipping, subtotal, tax);
        BigDecimal amount = subtotal.add(shipping).add(tax);
        PayPalPayment payment = new PayPalPayment(amount, "EUR", "Fleur des bach", paymentIntent);
        payment.items(items).paymentDetails(paymentDetails);
        addAppProvidedShippingAddress(payment);
        //--- set other optional fields like invoice_number, custom field, and soft_descriptor
        payment.custom("This is text that will be associated with the payment that the app can use.");
        return payment;
    }

    @OnClick(R.id.add_to_cart)
    public void submit(View view) {
        /*
         * PAYMENT_INTENT_SALE will cause the payment to complete immediately.
         * Change PAYMENT_INTENT_SALE to
         *   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
         *   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
         *     later via calls from your server.
         *
         * Also, to include additional payment details and an item list, see getStuffToBuy() below.
         */
        PayPalPayment thingToBuy = getStuffToBuy(PayPalPayment.PAYMENT_INTENT_SALE);

        /*
         * See getStuffToBuy(..) for examples of some available payment options.
         */

        Intent intent = new Intent(getActivity(), PaymentActivity.class);

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

        startActivityForResult(intent, REQUEST_CODE_PAYMENT);


        Product product = getProduct();
        //int id_address_delivery = product.getId
        int id_product = product.getIdProduct();
        CartProduct cartProduct = new CartProduct();
        cartProduct.setIdCart(22);
        cartProduct.setIdAddressDelivery(6);
        cartProduct.setIdProduct(id_product);
        cartProduct.setQuantity(1);
        cartProduct.setIdShop(1);
        //cartProduct.setIdProductAttribute(getProduct().getIdProductAttribute());
        SharedPreferences preferences = getActivity().getSharedPreferences("customer", Context.MODE_PRIVATE);
        getSpiceManager().execute(new CartAddProductPostRequest(cartProduct, "up", preferences.getString(Token.BEARER_TOKEN, "")), new RequestListener<CartProduct>() {
            @Override
            public void onRequestFailure(SpiceException e) {
                System.out.println(e);
            }

            @Override
            public void onRequestSuccess(CartProduct cp) {
                if (cp != null) {
                    if (cp.isCreate() || cp.isUpdated())
                        AppMsg.makeText(getActivity(), "Votre produit � bien �t� ajout�", AppMsg.STYLE_CONFIRM).show();
                }

            }

        });
        // CartDisplayListFragment cartListFragment =
        // CartDisplayListFragment.newInstance();
        // fragmentListner.replaceFragment(cartListFragment, null);
        /*
		 * if(isOnRequestSuccessFinished){ CartDisplayListFragment
		 * cartListFragment = CartDisplayListFragment.newInstance();
		 * fragmentListner.replaceFragment(cartListFragment, null); }
		 */

    }

    private void startPaypal() {
        Intent intent = new Intent(getActivity(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        getActivity().startService(intent);
    }

    public class ProductRequestListner implements RequestListener<Product> {

        @Override
        public void onRequestFailure(SpiceException fail) {
            Toast.makeText(getActivity(), "FAIL", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onRequestSuccess(Product product) {
            isOnRequestSuccessFinished = true;
            setProduct(product);
            setUpUi();
        }
    }


}
