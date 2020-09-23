package com.board_of_ads.configs;

import com.board_of_ads.model.City;
import com.board_of_ads.model.Region;
import com.board_of_ads.model.Role;
import com.board_of_ads.model.User;
import com.board_of_ads.service.interfaces.KladrService;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;
    private final KladrService kladrService;

    @PostConstruct
    private void init() throws IOException {
        initUsers();
        streamKLADR();
    }

    private void initUsers() {

        if (roleService.getRoleByName("ADMIN") == null) {
            roleService.saveRole(new Role("ADMIN"));
        }
        if (roleService.getRoleByName("USER") == null) {
            roleService.saveRole(new Role("USER"));
        }
        if (userService.getUserByEmail("admin@mail.ru") == null) {
            User admin = new User();
            admin.setEmail("admin@mail.ru");
            admin.setPassword("admin");
            Set<Role> roleAdmin = new HashSet<>();
            roleAdmin.add(roleService.getRoleByName("ADMIN"));
            admin.setRoles(roleAdmin);
            userService.saveUser(admin);
        }
        if (userService.getUserByEmail("user@mail.ru") == null) {
            User user = new User();
            user.setEmail("user@mail.ru");
            user.setPassword("user");
            Set<Role> roleAdmin = new HashSet<>();
            roleAdmin.add(roleService.getRoleByName("USER"));
            user.setRoles(roleAdmin);
            userService.saveUser(user);
        }
    }

    private void streamKLADR() throws IOException {
        Set<FileInputStream> streamKLADR = new HashSet<>();
        FileInputStream fileInputStream_1 = new FileInputStream("src/main/resources/kladr/KLADR_1.xls");
        streamKLADR.add(fileInputStream_1);
        FileInputStream fileInputStream_2 = new FileInputStream("src/main/resources/kladr/KLADR_2.xls");
        streamKLADR.add(fileInputStream_2);
        FileInputStream fileInputStream_3 = new FileInputStream("src/main/resources/kladr/KLADR_3.xls");
        streamKLADR.add(fileInputStream_3);
        FileInputStream fileInputStream_4 = new FileInputStream("src/main/resources/kladr/KLADR_4.xls");
        streamKLADR.add(fileInputStream_4);
        Iterator<FileInputStream> iterator = streamKLADR.iterator();
        while (iterator.hasNext()) {
            FileInputStream fileInputStream = iterator.next();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            Workbook workbook = new HSSFWorkbook(bufferedInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getCell(1).getStringCellValue().equals("обл")
                        || row.getCell(1).getStringCellValue().equals("Респ")
                        || row.getCell(1).getStringCellValue().equals("край")
                        || row.getCell(1).getStringCellValue().equals("АО")
                        || row.getCell(1).getStringCellValue().equals("Аобл")
                        || (row.getCell(1).getStringCellValue().equals("г")
                        & (row.getCell(0).getStringCellValue().equals("Москва")
                        || row.getCell(0).getStringCellValue().equals("Санкт-Петербург")
                        || row.getCell(0).getStringCellValue().equals("Байконур")
                        || row.getCell(0).getStringCellValue().equals("Севастополь")))) {
                    String regionFormSubject = null;
                    switch (row.getCell(1).getStringCellValue()) {
                        case "обл":
                            regionFormSubject = "Область";
                            break;
                        case "край":
                            regionFormSubject = "Край";
                            break;
                        case "Респ":
                            regionFormSubject = "Республика";
                            break;
                        case "АО":
                            regionFormSubject = "Автономный округ";
                            break;
                        case "Аобл":
                            regionFormSubject = "Автономная область";
                            break;
                        case "г":
                            regionFormSubject = "Город";
                            break;
                    }
                    if (!kladrService.existsRegionByName(row.getCell(0).getStringCellValue())) {
                        kladrService.saveRegion(new Region(row.getCell(0).getStringCellValue(), row.getCell(2).getStringCellValue().substring(0, 2), regionFormSubject));
                    }
                }
                if (row.getCell(1).getStringCellValue().equals("г")
                        & !(row.getCell(0).getStringCellValue().equals("Москва")
                        || row.getCell(0).getStringCellValue().equals("Санкт-Петербург")
                        || row.getCell(0).getStringCellValue().equals("Байконур")
                        || row.getCell(0).getStringCellValue().equals("Севастополь"))) {

                    if (!kladrService.existsCityByCityNameAndRegion(row.getCell(0).getStringCellValue(), kladrService.getRegionByRegionNumber(row.getCell(2).getStringCellValue().substring(0, 2)))) {
                        kladrService.saveCity(new City(row.getCell(0).getStringCellValue(), kladrService.getRegionByRegionNumber(row.getCell(2).getStringCellValue().substring(0, 2)), "Город"));
                    }
                }
            }
            fileInputStream.close();
        }
    }

}
