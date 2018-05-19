package com.ilesanmi.oluwole.bakingapplication.data;

import com.ilesanmi.oluwole.bakingapplication.data.db.DbHelper;
import com.ilesanmi.oluwole.bakingapplication.data.network.ApiHelper;
import com.ilesanmi.oluwole.bakingapplication.data.pref.PreferenceHelper;

/**
 * Created by abayomi on 19/03/2018.
 */

public interface DataManager extends ApiHelper, DbHelper,PreferenceHelper {

}
