package ru.gb.WishList.files;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import ru.gb.WishList.entities.Gift;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;

public class ExcelBuilder extends AbstractXlsxView {
    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildExcelDocument(Map model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"wishlist.xls\"");

        @SuppressWarnings("unchecked")
        List<Gift> wishlist = (List<Gift>) model.get("wishlist");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Список подарков");

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("№");
        header.createCell(1).setCellValue("Название");
        header.createCell(2).setCellValue("Стоимость");
        header.createCell(3).setCellValue("Описание");
        header.createCell(4).setCellValue("Рейтинг");
        header.createCell(5).setCellValue("Приоритет подарка");
        header.createCell(6).setCellValue("Статус подарка");
        header.createCell(7).setCellValue("Комментарий хозяина подарка");

        // Create data cells
        int rowCount = 1;
        for (Gift gift : wishlist) {
            Row giftRow = sheet.createRow(rowCount);
            giftRow.createCell(0).setCellValue(rowCount++);
            giftRow.createCell(1).setCellValue(gift.getProduct().getName());
            giftRow.createCell(2).setCellValue(gift.getProduct().getPrice().doubleValue());
            giftRow.createCell(3).setCellValue(gift.getProduct().getDescription());
            giftRow.createCell(4).setCellValue(gift.getProduct().getScore());
            giftRow.createCell(5).setCellValue(gift.getPriority().label);
            giftRow.createCell(6).setCellValue(gift.getStatus().label);
            giftRow.createCell(7).setCellValue(gift.getComment());
        }

    }
}
