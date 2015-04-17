package dw.fdb.com.fdbapp.listner;

import android.util.Log;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.apache.http.HttpStatus;

import de.greenrobot.event.EventBus;
import dw.fdb.com.fdbapp.OauthError;
import dw.fdb.com.fdbapp.model.AuthTokenException;
import retrofit.RetrofitError;

public abstract class BaseRequestLisner<T> implements RequestListener<T>, OauthError {
    @Override
    public void onRequestFailure(SpiceException e) {
        if (e.getCause() instanceof RetrofitError) {
            RetrofitError error = (RetrofitError) e.getCause();

            if (!error.isNetworkError()) {
                switch (error.getResponse().getStatus()) {
                    case HttpStatus.SC_UNAUTHORIZED:
                        AuthTokenException authTokenException = (AuthTokenException) error.getBodyAs(AuthTokenException.class);
                        onInvalidToken(authTokenException);
                        if (authTokenException.getErrorDescription().equals(OauthError.EXPIRED_MSG)) {
                            onExpiredToken(authTokenException);
                        }

                        break;

                    case HttpStatus.SC_BAD_REQUEST:
                        AuthTokenException badRequest = (AuthTokenException) error.getBodyAs(AuthTokenException.class);
                        break;
                }
                // Le broadcast peut commencer
                //EventBus.getDefault().post(new StickyEvent(String.valueOf(error.getResponse().getStatus())));
            }
        }

    }

    @Override
    public void onExpiredToken(AuthTokenException authTokenException) {
        if (authTokenException != null) {
            EventBus.getDefault().post(authTokenException);
        }


    }

    @Override
    public void onInvalidToken(AuthTokenException authTokenException) {
        if (authTokenException != null) {
            EventBus.getDefault().post(authTokenException);
        }

    }

    @Override
    public void onRequestSuccess(T t) {
        Log.d("BaseRequestLisner", "onRequestSuccess");
        if (t != null) {
            System.out.println("b�b�b�b�");
        }


    }


}
