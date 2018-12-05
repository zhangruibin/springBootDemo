package com.example.test.poiRead;

import com.example.entity.ContentValueEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/*
* @ClassName PoiWrite2
*@Description TODO
*@Author zhangrui
*@Date 2018/11/20 15:16
*@Version 
*/
public class PoiWrite2 implements Callable {
    private CountDownLatch countDownLatch;
    private Sheet sheet;
    private int start;
    private int end;
    private String key;
    private Map<String,List<ContentValueEntity>> map;

    public PoiWrite2(CountDownLatch countDownLatch, Sheet sheet, int start, int end, String key, Map<String,
            List<ContentValueEntity>> map) {
        this.countDownLatch = countDownLatch;
        this.sheet = sheet;
        this.start = start;
        this.end = end;
        this.key = key;
        this.map = map;
    }

    @Override
    public Map<String, List<ContentValueEntity>> call() throws Exception {
        List<ContentValueEntity> list = new ArrayList<>();
        for (int rowNum = this.start;rowNum<=this.end;rowNum++){
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
        this.map.put(key,list);
        //计数器 减一
        this.countDownLatch.countDown();
        return this.map;
    }
}
