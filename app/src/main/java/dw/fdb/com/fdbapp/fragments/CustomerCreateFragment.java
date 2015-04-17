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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.devspark.appmsg.AppMsg;
import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.apache.http.HttpStatus;
import org.joda.time.DateTime;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.model.Customer;
import dw.fdb.com.fdbapp.model.CustomerException;
import dw.fdb.com.fdbapp.request.CustomerCreateRequest;
import retrofit.RetrofitError;

public class CustomerCreateFragment extends BaseFragment implements CalendarDatePickerDialog.OnDateSetListener {

    public static final String TAG = "CustomerCreateFragment";
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
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
    @InjectView(R.id.radioSex)
    RadioGroup radioSex;
    @InjectView(R.id.year)
    EditText year;
    @InjectView(R.id.day)
    EditText day;
    @InjectView(R.id.mounth)
    EditText mounth;

    View view;
    FragmentListner fragmentListner;

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

    @OnClick(R.id.date_picker)
    public void onClick() {
        popDatePicker();
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
        switch (radioSex.getCheckedRadioButtonId()) {
            case R.id.m:
                customer.setGender(1);
                break;

            case R.id.mme:
                customer.setGender(2);
                break;
        }
        customer.setBirthday(year.getText() + "-" + mounth.getText()+ "-" +  day.getText() );
        return customer;
        }

        @Override
        public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
            MenuItem item = menu.add(Menu.NONE, R.id.add, 10, R.string.add);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            super.onCreateOptionsMenu(menu, inflater);

        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.add:
                    perform_request();
            }
            return true;
        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            ButterKnife.reset(this);
        }

    private void popDatePicker() {
        FragmentManager fm = getChildFragmentManager();
        DateTime now = DateTime.now();
        CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog.newInstance(CustomerCreateFragment.this, now.getYear(), now.getMonthOfYear() -1, now.getDayOfMonth());
        calendarDatePickerDialog.show(fm, FRAG_TAG_DATE_PICKER);
    }

    private void perform_request() {
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest(getCustomer());
        getSpiceManager().execute(customerCreateRequest, new CreateCustomerListner());
    }

    @Override
    public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int year, int mounth, int day) {
        this.day.setText(""+day);
        this.mounth.setText(""+mounth);
        this.year.setText(""+year);

    }

    public class CreateCustomerListner implements RequestListener<Customer> {

        @Override
        public void onRequestFailure(SpiceException e) {
            if (e.getCause() instanceof RetrofitError) {
                RetrofitError error = (RetrofitError) e.getCause();
                if (!error.isNetworkError()) {
                    switch (error.getResponse().getStatus()) {
                        case HttpStatus.SC_NOT_FOUND:
                            CustomerException customerException = (CustomerException) error.getBodyAs(CustomerException.class);
                            AppMsg.makeText(getActivity(), "e", AppMsg.STYLE_CONFIRM).show();
                        break;

                    }

                }
            }
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
