package action.controller;

import action.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * @author ruihui.li
 * @version V1.0
 * @Description:
 * @date 2017/11/6
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index.do")
    public String home(int choose) throws Exception{
        try {
            userService.testException(choose);
        }catch (Exception e){

        }
        return "/home/index";
    }

    @RequestMapping("/upload.do")
    public String upload(HttpServletRequest request, Model model)throws Exception{
        String path = "E:\\file\\";
        MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mr.getFileMap();
        Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
        if (null != entries && entries.size() > 0){
            for (Map.Entry<String,MultipartFile> entry : entries){
                MultipartFile file = entry.getValue();
                if(file.getSize() > 0) {
                    String originalFilename = file.getOriginalFilename();
                    System.out.println(originalFilename);
                    String newFileName = new StringBuilder(path).append(originalFilename).toString();
                    File newFile = new File(newFileName);
                    file.transferTo(newFile);
                }
            }
        }
        fileMap.size();
        return "/home/index";
    }

}
