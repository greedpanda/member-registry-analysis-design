package model.persistence;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import model.domain.Member;





/**
 * Persistence class.
 */
public class PersistenceManager implements PersistenceInterface {

  enum CorruptionWarnMessage {
      CORRUPTED_FILE("\n\n\nThe file that the application is trying to load is corrupted.\n"
              + "A duplicated Member ID is present.\n\n\n"),
      CORRUPTED_DATA("\n\n\nThe information that the application is trying to save is corrupted.\n"
              + "A duplicated Member ID is present.\n\n\n");

    public final String str;

    CorruptionWarnMessage(String str) {
      this.str = str;
    }
  }

  private ObjectMapper mapper; // maps from json
  private ObjectWriter writer; // writes to json
  private File file;

  /**
   * Persistence constructor.
   */
  public PersistenceManager(String filePath) {
    file = new File(filePath);
    mapper = new ObjectMapper().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
    mapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, true);
    writer = mapper.writer(new DefaultPrettyPrinter());
  }

  @Override
  public Map<String, Member> loadData() {
    Map<String, Member> map = null;
    try {
      map = mapper.readValue(file, new TypeReference<Map<String, Member>>() {
      });
    } catch (IOException e) {
      System.out.println(PersistenceManager.CorruptionWarnMessage.CORRUPTED_FILE.str);
      Runtime.getRuntime().halt(0);
      //e.printStackTrace();
    }
    return map;
  }

  @Override
  public boolean saveData(Iterator<Map.Entry<String, Member>> map) {
    Map<String, Member> m = new HashMap<>();
    while (map.hasNext()) {
      Map.Entry<String, Member> e = map.next();
      m.put(e.getKey(), e.getValue());
    }
    boolean flag = false;
    try {
      writer.writeValue(file, m);
      flag = true;
    } catch (IOException e) {
      System.out.println(PersistenceManager.CorruptionWarnMessage.CORRUPTED_DATA.str);
      Runtime.getRuntime().halt(0);
      //e.printStackTrace();
    }
    return flag;
  }

}
