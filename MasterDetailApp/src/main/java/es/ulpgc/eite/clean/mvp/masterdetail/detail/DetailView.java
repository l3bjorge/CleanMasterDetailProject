package es.ulpgc.eite.clean.mvp.masterdetail.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.masterdetail.R;
import es.ulpgc.eite.clean.mvp.masterdetail.app.ModelItem;
import es.ulpgc.eite.clean.mvp.masterdetail.master.MasterView;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MasterView}.
 */
public class DetailView
    extends GenericActivity<Detail.PresenterToView, Detail.ViewToPresenter, DetailPresenter>
    implements Detail.PresenterToView {

  private ModelItem item;
  private Toolbar toolbar;
  private CollapsingToolbarLayout toolbarLayout;
  private AppBarLayout appbarLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_detail);
    Log.d(TAG, "calling onCreate()");

    toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
    setSupportActionBar(toolbar);

    // Show the Up button in the action bar.
    ActionBar actionbar = getSupportActionBar();
    if (actionbar != null) {
      actionbar.setDisplayHomeAsUpEnabled(true);
    }

    toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
    appbarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
    appbarLayout.setExpanded(true);
  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(DetailPresenter.class, this);
    Log.d(TAG, "calling onResume()");

    item = getPresenter().getItem();

    if (toolbarLayout != null && item != null) {
      toolbarLayout.setTitle(item.getContent());
    }

    // Show the dummy content as text in a TextView.
    if (item != null) {
      ((TextView) findViewById(R.id.item_detail)).setText(item.getDetails());
    }

  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Log.d(TAG, "calling onBackPressed()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "calling onDestroy()");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_detail, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      finishScreen();
      return true;
    } else if (id == R.id.action_delete) {
      getPresenter().onDeleteActionClicked();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }


  /////////////////////////////////////////////////////////////////////////////////////
  // Presenter To View ///////////////////////////////////////////////////////////////


  @Override
  public void finishScreen() {
    Log.d(TAG, "calling finishScreen()");
    finish();
  }

  @Override
  public void hideToolbar() {
    toolbar.setVisibility(View.GONE);
    appbarLayout.setExpanded(false);
  }

}
