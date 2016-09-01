/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control;

/**
 *
 * @author OCTI01
 */
public class GerenciarJanela {

    public static Janela janelaAtual;
    public static Janela janelaAntiga;

    public enum Janela {
        INICIO("", "Inicio"),
        SPONSORARUNNER("Sponsor a runner", "SponsorARunner"),
        SPONSORSHIPCONFIRMATION("Sponsorship Confirmation", "SponsorshipConfirmation"),
        WANTFINDOUTMORE("Find out more information", "WantFindOutMore"),
        LISTOFCHARITIES("List of charities", "ListOfCharity"),
        LOGIN("Login", "Login"),
        MENUADMINISTRATOR("Administrator menu", "AdministratorMenu"),
        MENUCOORDINATOR("Coordinator menu", "CoordinatorMenu"),
        MENURUNNER("Runner menu", "RunnerMenu"),
        REGISTERRUNNER("Register as a runner", "RegisterRunner"),
        WANTBEARUNNER("Register as a runner", "WantBeARunner"),
        REGISTEREVENT("Register for an event", "RegisterEvent"),
        RUNNERREGISTRATIONCONFIRMATION("Registration confirmation", "RegistrationConfirmation"),
        EDITPROFILE("Edit runner profile", "EditProfile"),
        MYRACERESULT("My Race results", "MyRaceResults"),
        VIEWALLRACERESULT("Previous race results", "PreviousRaceResult"),
        HOWLONGMARATHON("How long is a marathon", "HowLongMarathon"),
        ABOUTMARATHONSKILL("About Marathon Skills 2015", "AboutMarathonSkills"),
        INTERACTIVEMAP("Interactive map", "InteractiveMap"),
        MYSPONSORSHIP("My sponsorship", "MySponsorship"),
        RUNNERMANAGEMENT("Runner management", "RunnerManagement"),
        MANAGEARUNNER("Manage a runner", "ManageARunner"),
        PREVIEWCERTIFICATE("Certificate preview", "CertificatePreview"),
        SPONSORSHIPOVERVIEW("Sponsorship overview", "SponsorshipOverView"),
        USERMANAGEMENT("User management", "UserManagement"),
        EDITUSER("Edit a user", "EditUser"),
        ADDUSER("Add a new user", "AddUser"),
        MANAGECHARITIES("Manage charities", "ManageCharities"),
        MANAGERCHARITY("Add/Edit charity", "ManagerCharity"),
        VOLUNTEERMANAGER("Volunteer management", "VolunteerManagement"),
        IMPORTVOLUNTEERS("Import volunteers", "ImportVolunteers"),
        BMICALCULATOR("BMI calculator", "BMICalculator"),
        BMRCALCULATOR("BMR calculator", "BMRCalculator");

        private String titulo;
        private String arquivo;

        private Janela(String titulo, String arquivo) {
            this.titulo = titulo;
            this.arquivo = arquivo;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getArquivo() {
            return arquivo;
        }

        public void setArquivo(String arquivo) {
            this.arquivo = arquivo;
        }

        @Override
        public String toString() {
            return getArquivo();
        }
    }
}
