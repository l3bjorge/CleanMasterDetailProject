package es.ulpgc.eite.clean.mvp.masterdetail.app;

/**
 * Created by Luis on 19/11/16.
 */

public class ModelItem {

  private String id;
  private String content;
  private String details;

  public ModelItem(String id, String content, String details) {
    this.id = id;
    this.content = content;
    this.details = details;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ModelItem){
      ModelItem item = (ModelItem) obj;
      if(item.getId() == getId()){
        return true;
      }
    }
    return false;
  }


  @Override
  public String toString() {
    return content;
  }
}
