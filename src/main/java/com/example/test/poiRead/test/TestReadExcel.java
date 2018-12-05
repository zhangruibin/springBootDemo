package com.example.test.poiRead.test;

import com.example.entity.ContentValueEntity;
import com.example.test.poiRead.PoiWrite;
import com.example.test.poiRead.PoiWrite2;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.*;

/*
* @ClassName TestReadExcel
*@Description TODO
*@Author zhangrui
*@Date 2018/11/20 15:18
*@Version 
*/
public class TestReadExcel {
    private final static File file = new File("C:/Users/Administrator/Desktop/工作簿.xls");
    public static void main(String[] args){
        try {
            operaterExcel3();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author zhrb
     * @Description 向excel中写入2000条数据
     * @Date 下午1:28 2018/11/20
     * @Param []
     * @return void
     **/
    public static void operaterExcel() throws Exception{
        //把文件读入输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        /**创建工作簿，这一个对象代表着对应的一个Excel文件
         * 用 WorkbookFactory 创建workbook
         * 就 不用判断 2003(xls)（HSSFWorkbook）
         * 与2007(slsx)(XSSFWorkbook)*/
        Workbook wb = WorkbookFactory.create(fileInputStream);
        //读取sheet
        Sheet sheet = wb.getSheetAt(0);
        //获得CreationHelper对象,这个应该是一个帮助类
        CreationHelper creationHelper = wb.getCreationHelper();
        //单元格样式类
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
        //单元格对其方式
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.ALIGN_GENERAL);
        //设置列宽
        sheet.setColumnWidth(0,9999);
        for (int rowNum = 1;rowNum<=2000;rowNum++){
            //创建行
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0;cellNum<=5;cellNum++){
                if (cellNum == 0){
                    Cell cell = row.createCell(cellNum);
                    cell.setCellValue(new Date());
                    cell.setCellStyle(cellStyle);
                }else if (cellNum == 1){
                    row.createCell(1).setCellValue(111);
                }else if (cellNum == 2){
                    row.createCell(2).setCellValue("一个字符串");
                }else if (cellNum == 3){
                    row.createCell(3).setCellValue(true);
                }else if (cellNum == 4){
                    row.createCell(4).setCellValue(Cell.CELL_TYPE_NUMERIC);
                }else{
                    row.createCell(5).setCellValue(false);
                }

            }
        }
        //创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        //写出文件
        wb.write(fileOutputStream);
        //关闭输出流
        fileOutputStream.close();
    }
    /**
     * @Author zhrb
     * @Description CyclicBarrier + 线程池处理
     * @Date 下午2:16 2018/11/20
     * @Param []
     * @return void
     **/
    public static void operaterExcel2(){

        FileInputStream fileInputStream = null;
        Workbook wb = null;
        Sheet sheet = null;
        //创建的线程数
        int threadNum = 4;
        //存放最终结果的map
        final Map<String,List<ContentValueEntity>> map = new HashMap<>(threadNum);
        /** 使用线程池进行线程管理（面试必问）
         *
         */
        final ExecutorService es = Executors.newCachedThreadPool();

        try {
            //把文件读入输入流
            fileInputStream = new FileInputStream(file);
            /**创建工作簿，这一个对象代表着对应的一个Excel文件
             * 用 WorkbookFactory 创建workbook
             * 就 不用判断 2003(xls)（HSSFWorkbook）
             * 与2007(slsx)(XSSFWorkbook)
             * */
            wb = WorkbookFactory.create(fileInputStream);
            //读取sheet
            sheet = wb.getSheetAt(0);
            /**第一行数据（第一行是列名，所以开始行+1）
             * */
            int first = sheet.getFirstRowNum()+1;
            //最后一行
            int last = sheet.getLastRowNum();
            //全部的行数
            int totalNum = last - first + 1;
            //每个线程处理的行数
            int numOfThread = totalNum/threadNum;
//            int lastThreadNum = totalNum - (threadNum-1)*numOfThread;

            /**获取开始时间
             *
             */
            final long startTime=System.currentTimeMillis();

            /**回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
             叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用
             *
             *  假若有若干个线程都要进行写数据操作，
             *  并且只有所有线程都完成写数据操作之后，
             *  这些线程才能继续做后面的事情，
             *  此时就可以利用CyclicBarrier了：
             */
            CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, new Runnable() {
                @Override
                public void run() {
                    /**当所有线程执行完毕后，便执行此方法
                     *
                     */
                    List<ContentValueEntity> list1 = map.get("1");
                    List<ContentValueEntity> list2 = map.get("2");
                    List<ContentValueEntity> list3 = map.get("3");
                    List<ContentValueEntity> list4 = map.get("4");
                    List<ContentValueEntity> combineList = new ArrayList<>();
                    combineList.addAll(list1);
                    combineList.addAll(list2);
                    combineList.addAll(list3);
                    combineList.addAll(list4);
                    /**获取结束时间
                     *
                     */
                    long endTime=System.currentTimeMillis();
                    /**程序运行时间
                     *
                     */
                    System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

                    System.out.println("ResultList====="+combineList);

                    es.shutdown();
                }
            });


            /**这里提交线程去执行，有两个方法，1、submit 2、excuse
             *
             */
            Future<Map<String,List<ContentValueEntity>>> data1 = es.submit(new PoiWrite(cyclicBarrier, sheet, 1, numOfThread,"1",
                    map));
            Future<Map<String,List<ContentValueEntity>>> data2 = es.submit(new PoiWrite(cyclicBarrier, sheet, numOfThread+1, 2*numOfThread,"2",
                    map));
            Future<Map<String,List<ContentValueEntity>>> data3 = es.submit(new PoiWrite(cyclicBarrier, sheet, 2*numOfThread+1, 3*numOfThread,"3",
                    map));
            Future<Map<String,List<ContentValueEntity>>> data4 = es.submit(new PoiWrite(cyclicBarrier, sheet, 3*numOfThread+1, totalNum,"4",
                    map));


        }  catch (Exception e){

        }finally {

        }
    }


    /**
     * @Author zhrb
     * @Description CountDownLatch + 线程池处理
     * @Date 下午2:17 2018/11/20
     * @Param []
     * @return void
     **/
    public static void operaterExcel3(){

        FileInputStream fileInputStream = null;
        Workbook wb = null;
        Sheet sheet = null;
        //创建的线程数
        int threadNum = 4;
        //存放最终结果的map
        Map<String,List<ContentValueEntity>> map = new HashMap<>(threadNum);
        // 使用线程池进行线程管理
        ExecutorService es = Executors.newCachedThreadPool();
        /**使用计数栅栏
         * CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。
         * 比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，
         * 此时就可以利用CountDownLatch来实现这种功能了
         */
        CountDownLatch latch = new CountDownLatch(threadNum);

        try {
            //把文件读入输入流
            fileInputStream = new FileInputStream(file);
            /**创建工作簿，这一个对象代表着对应的一个Excel文件
             * 用 WorkbookFactory 创建workbook
             * 就 不用判断 2003(xls)（HSSFWorkbook）
             * 与2007(slsx)(XSSFWorkbook)
             * */
            wb = WorkbookFactory.create(fileInputStream);
            //读取sheet
            sheet = wb.getSheetAt(0);
            /**第一行数据（第一行是列名，所以开始行+1）
             * */
            int first = sheet.getFirstRowNum()+1;
            //最后一行
            int last = sheet.getLastRowNum();
            //全部的行数
            int totalNum = last - first + 1;
            //每个线程处理的行数
            int numOfThread = totalNum/threadNum;
            //int lastThreadNum = totalNum - (threadNum-1)*numOfThread;

            /**获取开始时间
             *
             */
            long startTime=System.currentTimeMillis();

            /**PoiWrite2 implements Callable  重写call方法，可以返回线程执行结果
             * 面试问线程的创建方式
             * 1、extends Thread 2、implements Runnable 3、implements Callable 4、线程池
             */
            Future<Map<String,List<ContentValueEntity>>> data1 = es.submit(new PoiWrite2(latch, sheet, 1, numOfThread,"1",
                    map));
            Future<Map<String,List<ContentValueEntity>>> data2 = es.submit(new PoiWrite2(latch, sheet, numOfThread+1, 2*numOfThread,"2",
                    map));
            Future<Map<String,List<ContentValueEntity>>> data3 = es.submit(new PoiWrite2(latch, sheet, 2*numOfThread+1, 3*numOfThread,"3",
                    map));
            Future<Map<String,List<ContentValueEntity>>> data4 = es.submit(new PoiWrite2(latch, sheet, 3*numOfThread+1, totalNum,"4",
                    map));

            /**阻塞直至所有线程完成操作
             * 在 PoiWrite2 中 使用 countDownLatch.countDown()，表示等待的任务 -1
             */
            latch.await();

            /**这里有点小问题，每段打出的结果不对啊。。。
             *
             */

//            Map<String,List<ContentValueEntity>> map1 = data1.get();
//            Map<String,List<ContentValueEntity>> map2 = data2.get();
//            Map<String,List<ContentValueEntity>> map3 = data3.get();
//            Map<String,List<ContentValueEntity>> map4 = data4.get();
//
////            for (Map.Entry<String,List<ContentValueEntity>> entry : map1.entrySet()){
////                System.out.println("111111111111111"+"Key = " + entry.getKey() + ", Value = " + entry.getValue());
////            }
////            for (Map.Entry<String,List<ContentValueEntity>> entry : map2.entrySet()){
////                System.out.println("222222222222222"+"Key = " + entry.getKey() + ", Value = " + entry.getValue());
////            }
////            for (Map.Entry<String,List<ContentValueEntity>> entry : map3.entrySet()){
////                System.out.println("33333333333333"+"Key = " + entry.getKey() + ", Value = " + entry.getValue());
////            }
////            for (Map.Entry<String,List<ContentValueEntity>> entry : map4.entrySet()){
////                System.out.println("44444444444444"+"Key = " + entry.getKey() + ", Value = " + entry.getValue());
////            }
////            for (Map.Entry<String,List<ContentValueEntity>> entry : map.entrySet()){
////                System.out.println("MAP========"+"Key = " + entry.getKey() + ", Value = " + entry.getValue());
////            }


            List<ContentValueEntity> list1 = map.get("1");
            List<ContentValueEntity> list2 = map.get("2");
            List<ContentValueEntity> list3 = map.get("3");
            List<ContentValueEntity> list4 = map.get("4");
            List<ContentValueEntity> combineList = new ArrayList<>();
            combineList.addAll(list1);
            combineList.addAll(list2);
            combineList.addAll(list3);
            combineList.addAll(list4);
//            for (Map.Entry<String,List<ContentValueEntity>> entry : map.entrySet()){
//                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//            }
            /**获取结束时间
             *
             */
            long endTime=System.currentTimeMillis();
            /**程序运行时间
             *
             */
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

            System.out.println("ResultList====="+combineList);

            es.shutdown();


        }  catch (Exception e){

        }
    }

    /**
     * @Author zhrb
     * @Description 单线程处理
     * @Date 下午2:18 2018/11/20
     * @Param []
     * @return void
     **/

    public static void operaterExcel4(){

        FileInputStream fileInputStream = null;
        Workbook wb = null;
        Sheet sheet = null;



        try {
            //把文件读入输入流
            fileInputStream = new FileInputStream(file);
            /**创建工作簿，这一个对象代表着对应的一个Excel文件
             * 用 WorkbookFactory 创建workbook
             * 就 不用判断 2003(xls)（HSSFWorkbook）
             * 与2007(slsx)(XSSFWorkbook)
             * */
            wb = WorkbookFactory.create(fileInputStream);
            //读取sheet
            sheet = wb.getSheetAt(0);
            /**第一行数据（第一行是列名，所以开始行+1）
             * */
            int first = sheet.getFirstRowNum()+1;
            //最后一行
            int last = sheet.getLastRowNum();

            List<ContentValueEntity> list = new ArrayList<>();
            /**获取开始时间
             *
             */
            long startTime=System.currentTimeMillis();
            for (int rowNum = first;rowNum<=last;rowNum++){
                Row row = sheet.getRow(rowNum);
                if (row == null){
                    continue;
                }
                ContentValueEntity entity = new ContentValueEntity();
                for (int cellNum = 0;cellNum<=5;cellNum++){
                    Cell cell = row.getCell(cellNum);
                    if (cell == null){
                        continue;
                    }
                    if (cellNum == 0){
                        entity.setNowTime(cell.getDateCellValue());
                    }else if (cellNum == 1){
                        entity.setSecondValue((int) cell.getNumericCellValue());
                    }else if (cellNum == 2){
                        entity.setThirdValue(cell.getStringCellValue());
                    }else if (cellNum == 3){
                        entity.setFourthValue(cell.getBooleanCellValue());
                    }else if (cellNum == 4){
                        entity.setFivthValue((int) cell.getNumericCellValue());
                    }else{
                        entity.setFourthValue(cell.getBooleanCellValue());
                    }
                }
                list.add(entity);
            }
            /**获取结束时间
             *
             */
            long endTime=System.currentTimeMillis();
            /**程序运行时间
             *
             */
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

            System.out.println("size=="+list.size()+"-------list ====="+list);


        }catch (Exception e){

        }
    }
}
