package es.ulpgc.eite.clean.mvp.masterdetail.master;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.masterdetail.app.ModelItem;


public class MasterModel
    extends GenericModel<Master.ModelToPresenter> implements Master.PresenterToModel {

  private static final int ITEM_COUNT = 9;

  // An array of items.
  public List<ModelItem> items = null;
  private boolean runningTask;
  private String errorMsg;


  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Master.ModelToPresenter presenter) {
    super.onCreate(presenter);
    Log.d(TAG, "calling onCreate()");

    errorMsg = "Error deleting item!";
  }

  /**
   * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
   * Usefull for kill/stop activities that could be running on the background Threads
   *
   * @param isChangingConfiguration Informs that a change is occurring on the configuration
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {

  }

  /////////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model //////////////////////////////////////////////////////////////


  /**
   * Llamado para recuperar los elementos a mostrar en la lista.
   * Si el contenido ya ha sido fijado antes, se notificará inmediatamente al presentador y,
   * sino es el caso, la notificación se realizará al finalizar la tarea que fija este contenido
   */
  @Override
  public void loadItems() {
    if(items == null && !runningTask) {
      startDelayedTask();
    } else {
      if(!runningTask){
        getPresenter().onLoadItemsTaskFinished(items);
      } else {
        getPresenter().onLoadItemsTaskStarted();
      }
    }
  }


  @Override
  public void deleteItem(ModelItem item) {
    if (items.contains(item)){
      items.remove(item);
    } else {
      getPresenter().onErrorDeletingItem(item);
    }
  }

  /**
   * Llamado para recuperar los elementos iniciales de la lista.
   * En este caso siempre se llamará a la tarea asíncrona
   */
  @Override
  public void reloadItems() {
    items = null;
    loadItems();
  }

  @Override
  public String getErrorMessage() {
    return errorMsg;
  }

  /////////////////////////////////////////////////////////////////////////////////////


  private void addItem(ModelItem item) {
    items.add(item);
  }

  private ModelItem createItem(int position) {
    return new ModelItem(String.valueOf(position), "Item " + position, makeDetails(position));
  }

  private String makeDetails(int position) {
    StringBuilder builder = new StringBuilder();
    builder.append("Details about Item: ").append(position).append("\n");
    for (int count = 0; count < position; count++) {
      builder.append("\nMore details information here.");
    }
    return builder.toString();
  }

  private void setItems(){
    items = new ArrayList();

    // Add some sample items.
    for (int count = 1; count <= ITEM_COUNT; count++) {
      addItem(createItem(count));
    }
  }

  /**
   * Llamado para recuperar los elementos a mostrar en la lista.
   * Consiste en una tarea asíncrona que retrasa un tiempo la obtención del contenido.
   * El modelo notificará al presentador cuando se inicia y cuando finaliza esta tarea.
   */
  private void startDelayedTask() {
    Log.d(TAG, "calling startDelayedTask()");
    runningTask = true;
    getPresenter().onLoadItemsTaskStarted();

    // Mock Hello: A handler to delay the answer
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        setItems();
        runningTask = false;
        getPresenter().onLoadItemsTaskFinished(items);
      }
    }, 5000);
  }

}
