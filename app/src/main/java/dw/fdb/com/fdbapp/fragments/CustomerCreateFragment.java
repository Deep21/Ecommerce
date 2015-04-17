package dw.fdb.com.fdbapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.joda.time.DateTime;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.model.Customer;

public class CustomerCreateFragment extends BaseFragment implements CalendarDatePickerDialog.OnDateSetListener{

    public static final String TAG = "CustomerCreateFragment";
    @InjectView(R.id.nom)
    TextView nom;
    @InjectView(R.id.prenom)
    TextView prenom;
    @InjectView(R.id.email)
    TextView email;
    @InjectView(R.id.pwd)
    TextView pwd;
    @InjectView(R.id.pwd_confirm)
    TextView pwd_confirm;
    View view;
    FragmentListner fragmentListner;
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";

    public static CustomerCreateFragment getInstance() {
        CustomerCreateFragment connexionFragment = new CustomerCreateFragment();
        return connexionFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentListner = (FragmentListner) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_account_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setFirstname(nom.getText().toString());
        customer.setLastname(prenom.getText().toString());
        customer.setEmail(email.getText().toString());
        customer.setPwd(pwd.getText().toString());
        customer.setPwdconfirmed(pwd_confirm.getText().toString());
        return customer;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.add(Menu.NONE, R.id.add, 10, R.string.add);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.add:
                perform_request();
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void perform_request() {
        FragmentManager fm = getChildFragmentManager();
        DateTime now = DateTime.now();
        CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog
                .newInstance(CustomerCreateFragment.this, now.getYear(), now.getMonthOfYear() - 1,
                        now.getDayOfMonth());
        calendarDatePickerDialog.show(fm, FRAG_TAG_DATE_PICKER);

//        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest(getCustomer());
//        getSpiceManager().execute(customerCreateRequest, new CreateCustomerListner());
    }

    @Override
    public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int i, int i2, int i3) {

    }

    public class CreateCustomerListner implements RequestListener<Customer> {

        @Override
        public void onRequestFailure(SpiceException e) {

        }

        @Override
        public void onRequestSuccess(Customer customer) {

            Log.e("CustomerCreateFragment", "" + customer);
//            if (customer.getCode() == 201) {
//                System.out.println("ok");
//                fragmentListner.removeFragment(CustomerCreateFragment.this);
//            }

        }

    }
}
