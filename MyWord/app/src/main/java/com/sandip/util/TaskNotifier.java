package com.sandip.util;

/**
 * Created by Sandeep on 12/29/2017.
 */

public interface TaskNotifier {
    void onSuccess(String mes);
    void onError(String mes);

}
