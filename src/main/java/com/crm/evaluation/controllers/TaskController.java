package com.crm.evaluation.controllers;

import java.util.List;

import org.apache.logging.log4j.status.StatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crm.evaluation.dtos.ProjectDTO;
import com.crm.evaluation.dtos.StatutDTO;
import com.crm.evaluation.dtos.TaskDTO;
import com.crm.evaluation.responses.ProjectResponse;
import com.crm.evaluation.responses.TaskResponse;
import com.crm.evaluation.services.ProjectService;
import com.crm.evaluation.services.StatutService;
import com.crm.evaluation.services.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @Autowired
    private StatutService statutService;

    @GetMapping
    public ModelAndView getTasks(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "per_page", defaultValue = "10") int perPage) throws Exception {
        ModelAndView modelAndView = new ModelAndView("template");
        modelAndView.addObject("page", "tasks/tasks");
        List<StatutDTO> statusList = statutService.getStatutsTasks();

        // Récupérer les projets
        
        TaskResponse tasks = taskService.getTasks(page, perPage);

        // Associer le nom du statut au projet en fonction de l'ID
        for (TaskDTO task : tasks.getData()) {
            for (StatutDTO statutDTO : statusList) {
                if(task.getStatusId().longValue() == statutDTO.getId()){
                    task.setStatusName(statutDTO.getTitle());
                }
            }
        }
        
        
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }
}