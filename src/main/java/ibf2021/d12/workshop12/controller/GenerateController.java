package ibf2021.d12.workshop12.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ibf2021.d12.workshop12.model.Generate;

@Controller
public class GenerateController {
    private Logger logger = LoggerFactory.getLogger(GenerateController.class);
    @GetMapping("/generate")
    public String showGenerateForm(Model model) {
        Generate generate = new Generate();
        //generate.setNumberVal(3);
        model.addAttribute("generate", generate);  // "generate" ties to the thymeleaf element
        return "generate"; // "generate" refers to the Generate object
    }

    @PostMapping("/generate")
    public String generateNumbers(@ModelAttribute Generate generate, Model model) {
        logger.info("From the form" + generate.getNumberVal());
        int numberRandomNumbers = generate.getNumberVal();
        if (numberRandomNumbers > 10) {
            model.addAttribute("errorMessage", "exceeded 10 numbers limit!");
            return "error";
        }
        String[] imgNumbers = {
            "number1.jpeg", "number2.jpeg", "number3.jpeg", "number4.jpeg", "number5.jpeg", 
            "number6.jpeg", "number7.jpeg", "number8.jpeg", "number9.jpeg", "number10.jpeg"
        };
        List<String> selectedImg = new ArrayList<String>();
        Random randNum = new Random();
        Set<Integer> uniqueGeneratedRandNumSet = new LinkedHashSet<Integer>();
        while(uniqueGeneratedRandNumSet.size() < numberRandomNumbers) {
            Integer resultOfRandNumbers = randNum.nextInt(generate.getNumberVal() + 1);
            uniqueGeneratedRandNumSet.add(resultOfRandNumbers);
        }

        Iterator<Integer> it = uniqueGeneratedRandNumSet.iterator();
        Integer currentElem = null;
        while(it.hasNext()) {
            currentElem = it.next();
            logger.info("currentElem > " + currentElem);
            selectedImg.add(imgNumbers[currentElem.intValue()]);
        }
        model.addAttribute("randomNumsResult", selectedImg.toArray());
        return "result";
    }
}