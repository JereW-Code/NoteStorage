package uk.ac.ucl.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Model
{
  private File dbFile;
  private String dbPath;

  public List<Note> getNotes() throws FileNotFoundException
  {
    List<Note> result = new ArrayList<>();
    try {
      Scanner dbFileReader = new Scanner(dbFile);
      while (dbFileReader.hasNextLine()) {
        String[] data = dbFileReader.nextLine().split(",");
        Note note = new Note(data[0], data[1], new ArrayList<>());
        result.add(note);
      }
      dbFileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    return result;
  }

  public void updateNote(String id, String name, String action) throws FileNotFoundException
  {
    StringBuffer stringBuffer = new StringBuffer();
    try {
      Scanner dbFileReader = new Scanner(dbFile);
      while (dbFileReader.hasNextLine()) {
        String thisLine = dbFileReader.nextLine();
        String[] data = thisLine.split(",");
        if(data[1].equals(id)){
          System.out.println("found target note: " + id + "   mode: " + action);
          thisLine = name + "," + data[1] + "," + data[2];
        }
        if(!(action.equals("DEL") && data[1].equals(id))) {
          stringBuffer.append(thisLine + "\n");
        } else {
          File file = new File(dbPath + id + ".txt");
          if (file.delete()) {
            System.out.println("File deleted successfully");
          }
        }
      }
      dbFileReader.close();

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    try {
      System.out.println("Write to noteList.csv...");
      FileWriter noteListWriter = new FileWriter(dbFile.getPath(), false);
      String result = String.valueOf(stringBuffer);
      System.out.println(result);
      noteListWriter.write(result);
      noteListWriter.close();
    } catch (IOException e){
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public Note getNoteById(String id) throws FileNotFoundException
  {
    Note result = null;
    try {
      Scanner dbFileReader = new Scanner(dbFile);
      while (dbFileReader.hasNextLine()) {
        String[] data = dbFileReader.nextLine().split(",");
        String thisId = data[1];
        if(thisId.equals(id)){
          result = new Note(data[0], data[1], new ArrayList<>());
        }
      }
      dbFileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    return result;
  }

  public void readFile(String filePath)
  {
    dbFile = new File(filePath + "noteList.csv");
    dbPath = filePath;
  }

  public String readTextFile(String textId){
    Path fileName = Path.of(dbPath + textId + ".txt");
    String strResult = null;
    try {
      strResult = Files.readString(fileName, StandardCharsets.UTF_8);
    } catch (IOException e){
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return strResult;
  }


  public void writeNoteFile(String id, String name, String text, File file) throws FileNotFoundException
  {
    try {
      Note note = getNoteById(id);
      // if note does not exist create a new one
      if(note == null) {
        UUID textFileId = UUID.randomUUID();


        String nodeInfo = name + ',' + textFileId + ',' + "nofile" + "\n";
        Files.writeString(Path.of(dbFile.getPath()), nodeInfo,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

        Files.writeString(Path.of(dbPath + textFileId + ".txt"), text,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);

      //otherwise, modify the existing text file and the index list
      } else {


        FileWriter writer = new FileWriter(dbPath + id + ".txt", false);
        writer.write(text);
        writer.close();
        updateNote(id, name, "MODIFY");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }


  // This also returns dummy data. The real version should use the keyword parameter to search
  // the data and return a list of matching items.
  public List<Note> searchFor(String keyword)
  {
    List<Note> result = new ArrayList<>();
    try {
      Scanner dbFileReader = new Scanner(dbFile);
      while (dbFileReader.hasNextLine()) {
        String[] data = dbFileReader.nextLine().split(",");
        Note note = new Note(data[0], data[1], new ArrayList<>());
        if(data[0].contains(keyword)) {
          result.add(note);
        }
      }
      dbFileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    return result;
  }
}
