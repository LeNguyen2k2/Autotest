package Autotest.pages;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Autotest.common.keywords.WebUI;

import java.io.File;
import java.io.IOException;

public class BasePage {

  private String getRepoName() {
    return repoName;
  }

  public void setRepoName(String repoName) {
    this.repoName = repoName;
  }

  private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

  private static final String OBJECT_REPO =
      System.getProperty("user.dir") + File.separator + "src"
          + File.separator + "main" + File.separator + "java"
          + File.separator + "Autotest" + File.separator + "object_repo"
          + File.separator;


  protected WebUI webUI;

  private String repoName;

  public BasePage(WebUI webUI) {
    this.webUI = webUI;
  }

  public String findElementObject(String elementName) {
    String object_repo_name = getRepoName();
    String object_repo_path = OBJECT_REPO + object_repo_name + ".json";
    File object_repo_file = new File(object_repo_path);
//    logger.info("Object repo: {}", object_repo_path);
    try {
      return JsonPath.read(object_repo_file, "$." + elementName).toString();
    } catch (PathNotFoundException | IOException e) {

    }
    return null;
  }
}
