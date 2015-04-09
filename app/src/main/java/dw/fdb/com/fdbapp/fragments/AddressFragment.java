package dw.fdb.com.fdbapp.fragments;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import butterknife.ButterKnife;
import dw.fdb.com.fdbapp.R;
import dw.fdb.com.fdbapp.model.Token;
import dw.fdb.com.fdbapp.request.OauthGetAccesTokenRequest;

public class AddressFragment extends BaseListFragment {

    public static final String TAG = "AddressFragment";
    public static final int TAG_ID = 1;

    public static AddressFragment getInstance() {
        AddressFragment connexionFragment = new AddressFragment();
        return connexionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
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
        if (getListAdapter() == null) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void perform_request(Token token) {
        OauthGetAccesTokenRequest accesTokenRequest = new OauthGetAccesTokenRequest(token);
        getSpiceManager().execute(accesTokenRequest, new GetAccesTokenRequest());
    }

    private void storeInSharedPref(Token token) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Editor edit = prefs.edit();
        edit.putString(Token.OAUTH_ACCES_TOKEN, token.getAccessToken());
        edit.putString(Token.OAUTH_REFRESH_TOKEN, token.getRefreshToken());
        edit.commit();
    }

    class GetAccesTokenRequest implements RequestListener<Token> {

        @Override
        public void onRequestFailure(SpiceException e) {

        }

        @Override
        public void onRequestSuccess(Token token) {
            if (token != null) {
                storeInSharedPref(token);
            }

        }
    }

}
