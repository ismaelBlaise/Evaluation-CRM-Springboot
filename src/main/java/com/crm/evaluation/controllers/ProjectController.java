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
import com.crm.evaluation.responses.ProjectResponse;
import com.crm.evaluation.services.ProjectService;
import com.crm.evaluation.services.StatutService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private StatutService statutService;

    @GetMapping
    public ModelAndView getProjects(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "per_page", defaultValue = "10") int perPage) throws Exception {
        ModelAndView modelAndView = new ModelAndView("template");
        modelAndView.addObject("page", "projects/projects");
        List<StatutDTO> statusList = statutService.getStatutsProject();

        // Récupérer les projets
        ProjectResponse projects = projectService.getProjects(page, perPage);

        // Associer le nom du statut au projet en fonction de l'ID
        for (ProjectDTO project : projects.getData()) {
            for (StatutDTO statutDTO : statusList) {
                if(project.getStatusId().longValue() == statutDTO.getId()){
                    project.setStatusName(statutDTO.getTitle());
                }
            }
        }
        
        
        modelAndView.addObject("projects", projects);
        return modelAndView;
    }
}