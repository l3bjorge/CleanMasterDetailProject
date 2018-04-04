package es.ulpgc.eite.clean.mvp.masterdetail.detail;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.masterdetail.app.ModelItem;

/**
 * Created by Luis on 12/11/16.
 */

public interface Detail {

  /**
   * Interfaz que permite  fijar el estado pasado desde la pantalla del maestro
   * en la pantalla del detalle
   */
  interface MasterToDetail {
    void onScreenStarted();
    void setToolbarVisibility(boolean visible);
    void setItem(ModelItem item);
  }

  /**
   * Interfaz que finaliza la pantalla del detalle y permite recopilar los valores necesarios
   * para actualizar el estado en la pantalla del maestro en función de las acciones tomadas
   * en la pantalla del detalle
   */
  interface DetailToMaster {
    void destroyView();
    ModelItem getItemToDelete();
  }

  /////////////////////////////////////////////////////////////////////////////////////


  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    ModelItem getItem();
    void onDeleteActionClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void hideToolbar();
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {
    ModelItem getItem();
    void setItem(ModelItem item);
  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }


}
