package jdk.file;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;

public class FileTest {

    @Test
    public void test() {
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void test1() throws IOException {
        File aaa = File.createTempFile("aaa", ".txt");
        //在内存中创建一个文件
        File file = new File("E:\\aaa.txt");
        file.createNewFile();
        //创建一个输出流，输出到本地磁盘
        FileOutputStream fileOutputStream = new FileOutputStream(aaa);
        //往本地磁盘中写文件
        fileOutputStream.write("imcoming".getBytes());
    }

    @Test
    public void test2() throws IOException {
        //以盘符开始的路径为绝对路径
        File file = new File("E:\\bbb.txt");
        boolean newFile = file.createNewFile();
        System.out.println(newFile);

/*        File file1 = new File("strageName.txt");
        boolean newFile1 = file1.createNewFile();
        System.out.println(newFile1)*/;
    }

    @Test
    public void test3() throws IOException {
        File file1 = new File("strageName.txt");
        //创建一个输出流，输出到本地磁盘
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        //往本地磁盘中写文件
        fileOutputStream.write("imcoming".getBytes());
    }

    /**
     * 使用fileWriter追加文件
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void test4() throws IOException, InterruptedException {
        File file1 = new File("strageName.txt");
        //true表示以追加而不是覆盖的形式写文件
        FileWriter fileWriter = new FileWriter(file1, true);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("aaa").append("|");
            stringBuilder.append("bbb").append("|");
            stringBuilder.append("ccc").append("|");
            stringBuilder.append("zzz").append("|");
            stringBuilder.append(System.getProperty("line.separator"));

            for (int i = 0; i < 10; i++) {
                fileWriter.append(stringBuilder);
                fileWriter.flush();
            }
        } finally {
            fileWriter.close();
        }

    }

    /**
     * 使用处理流PrintStrem输出文件
     */
    @Test
    public void test5() throws FileNotFoundException {
        File file1 = new File("strageName.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1, true);
        PrintWriter printWriter = new PrintWriter(fileOutputStream, true);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("qq").append("|");
            stringBuilder.append("ww").append("|");
            stringBuilder.append("ee").append("|");
            stringBuilder.append("rr").append("|");
            stringBuilder.append(System.getProperty("line.separator"));

            for (int i = 0; i < 10; i++) {
                printWriter.append(stringBuilder);
                printWriter.flush();
            }
        }finally {
            printWriter.close();
        }

    }

    /**
     * 创建文件时，父文件夹不存在如何处理
     */
    @Test
    public void test6() throws IOException {
        String basepath="accountFile";
        File accountFile=new File(basepath+"/8010019910/"+"20200313.txt");
        File parent=accountFile.getParentFile();
        System.out.println("父目录："+parent.getAbsolutePath());
        if (!parent.exists()){
            parent.mkdirs();
        }
        accountFile.createNewFile();
    }

    /**
     * 删除文件
     */
    @Test
    public void test7(){
        String basepath="accountFile";
        File accountFile=new File(basepath+"/8010019910/"+"20200313.txt");
        if (accountFile.exists()){
            boolean delete = accountFile.delete();
            System.out.println(delete);
        }
    }

    /**
     *
     */
    @Test
    public void test8() throws IOException {
        RandomAccessFile accountFile=new RandomAccessFile("strageName.txt","rw");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("head5").append("|");
        stringBuilder.append("head6").append("|");
        stringBuilder.append("head7").append("|");
        stringBuilder.append("head8").append("|");
        stringBuilder.append(System.getProperty("line.separator"));

        accountFile.write(stringBuilder.toString().getBytes());

    }

    @Test
    public void test9() throws FileNotFoundException {
        File file=new File("dasda.flag");
        System.out.println(file.getName());

        FileInputStream fileInputStream=new FileInputStream(file);
    }

    @Test
    public void test10(){
        System.out.println(StringUtils.isNumeric("-3 ".trim()));
        System.out.println(StringUtils.isNotBlank(""));
    }
}
