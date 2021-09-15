package kr.hs.dgsw.javaClass.nio;

import java.nio.file.*;
import java.util.List;

public class FileStudy {

  public static void studyPath() throws Exception {
    Path path = Paths.get("/Users/krung2/Documents/Github/JAVA_Class/src/kr/hs/dgsw/javaClass/nio/studyFile/hello.txt");
    path = Paths.get("/Users", "krung2", "Documents", "Github", "JAVA_Class", "src", "kr", "hs", "dgsw", "javaClass", "nio", "studyFile", "hello.txt");

    System.out.println(String.format("파일이름 : %s", path.getFileName()));
    System.out.println(String.format("부모 디렉토리 : %s", path.getParent()));
    System.out.println(String.format("중첩 경로 수 : %s", path.getNameCount()));

    System.out.println(String.format("디렉토리 여부 : %s", Files.isDirectory(path)));
    System.out.println(String.format("파일 여부 : %s", Files.isRegularFile(path)));
    System.out.println(String.format("마지막 수정 시간 : %s", Files.getLastModifiedTime(path)));
    System.out.println(String.format("파일 크기 : %s", Files.size(path)));
    System.out.println(String.format("소유자 : %s", Files.getOwner(path)));
    System.out.println(String.format("숨김 여부  : %s", Files.isHidden(path)));
    System.out.println(String.format("읽기 가능 여부 : %s", Files.isReadable(path)));
    System.out.println(String.format("쓰기 가능 여부 : %s", Files.isWritable(path)));
  }

  public static void studyFileManagement() throws Exception {
    System.out.println("파일/디렉토리 생성 삭제 공부");

    Path dirPath = Paths.get("/Users/krung2/Documents/Github/JAVA_Class/src/kr/hs/dgsw/javaClass/nio/studyFile/new");
    Path filePath = Paths.get("/Users/krung2/Documents/Github/JAVA_Class/src/kr/hs/dgsw/javaClass/nio/studyFile/new/new.txt");

    if (Files.notExists(dirPath)) {
      Files.createDirectories(dirPath);
    }

    if (Files.notExists(filePath)) {
      Files.createFile(filePath);
    }

    Path parentPath = Paths.get("/Users/krung2/Documents/Github/JAVA_Class/src/kr/hs/dgsw/javaClass/nio/studyFile");
    DirectoryStream<Path> directoryStream =
            Files.newDirectoryStream(parentPath);

    for (Path path: directoryStream) {
      if (Files.isDirectory(path)) {
        System.out.println(String.format("디렉토리 : %s", path.getFileName()));
        continue;
      }
      System.out.println(String.format("파일 : %s (%d)", path.getFileName(), Files.size(path)));
    }
  }

  public static void studyWatchService () throws Exception {
    System.out.println("Watch Service 공부");

    WatchService watchService = FileSystems.getDefault().newWatchService();

    Path path = Paths.get("/Users/krung2/Documents/Github/JAVA_Class/src/kr/hs/dgsw/javaClass/nio/studyFile");
    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

    while (true) {
      WatchKey watchKey = watchService.take();

      List<WatchEvent<?>> eventList = watchKey.pollEvents();
      for (WatchEvent<?> event : eventList) {
        Path eventPath = (Path)event.context();
        WatchEvent.Kind<?> kind = event.kind();

        if (kind == StandardWatchEventKinds.ENTRY_CREATE) System.out.println(String.format("파일 %s가 생성되었습니다", eventPath.getFileName()));
        if (kind == StandardWatchEventKinds.ENTRY_DELETE) System.out.println(String.format("파일 %s가 삭제되었습니다", eventPath.getFileName()));
        if (kind == StandardWatchEventKinds.ENTRY_MODIFY) System.out.println(String.format("파일 %s가 수정되었습니다", eventPath.getFileName()));
      }

      boolean valid = watchKey.reset();
      if (!valid) break;
    }
  }

  public static void main(String[] args) {
    try {
      studyPath();
      studyFileManagement();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
