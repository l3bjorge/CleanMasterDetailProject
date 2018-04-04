package es.ulpgc.eite.clean.mvp.masterdetail.app;

import es.ulpgc.eite.clean.mvp.masterdetail.detail.Detail;
import es.ulpgc.eite.clean.mvp.masterdetail.master.Master;

/**
 * Created by imac on 23/1/18.
 */

public interface Mediator {

  interface Lifecycle {

    void startingMasterScreen(Master.ToMaster presenter);
    void resumingMasterScreen(Master.DetailToMaster presenter);
    void startingDetailScreen(Detail.MasterToDetail presenter);
  }

  interface Navigation {

    void backToMasterScreen(Detail.DetailToMaster presenter);
    void goToDetailScreen(Master.MasterToDetail presenter);
  }

}
