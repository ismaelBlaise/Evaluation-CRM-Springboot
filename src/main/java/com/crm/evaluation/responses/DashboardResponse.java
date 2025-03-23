package com.crm.evaluation.responses;

import lombok.Data;

@Data
public class DashboardResponse {

    private int nbClients;
    private int nbProjects;
    private int nbTasks;
    private int nbOffers;
    private int nbInvoices;
    private int nbPayments;
    private int nbInvoiceLines;

    // Constructeur, getters, setters
    public DashboardResponse(int nbClients, int nbProjects, int nbTasks, int nbOffers, 
                             int nbInvoices, int nbPayments, int nbInvoiceLines) {
        this.nbClients = nbClients;
        this.nbProjects = nbProjects;
        this.nbTasks = nbTasks;
        this.nbOffers = nbOffers;
        this.nbInvoices = nbInvoices;
        this.nbPayments = nbPayments;
        this.nbInvoiceLines = nbInvoiceLines;
    }

}
