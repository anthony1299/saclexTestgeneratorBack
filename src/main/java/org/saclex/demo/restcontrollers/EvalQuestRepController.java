package org.saclex.demo.restcontrollers;

import org.saclex.demo.entities.EvalQuestRep;
import org.saclex.demo.service.EvalQuestRepService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("evalquest/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EvalQuestRepController {
    private final EvalQuestRepService evalQuestRepService;

    public EvalQuestRepController(EvalQuestRepService evalQuestRepService) {
        this.evalQuestRepService=evalQuestRepService;
    }

    @GetMapping("evalquestbyeval/{ideval}")
    public List<EvalQuestRep> getEvalQuest(@PathVariable Long ideval) {
        System.out.println(ideval);
        return evalQuestRepService.findByEval(ideval);
    }

}
