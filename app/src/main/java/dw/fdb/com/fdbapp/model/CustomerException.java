package dw.fdb.com.fdbapp.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Starlight on 17/04/2015.
 */
public class CustomerException {
    @Expose
    private List<String> message = new ArrayList<String>();

    /**
     *
     * @return
     * The message
     */
    public List<String> getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(List<String> message) {
        this.message = message;
    }
}
