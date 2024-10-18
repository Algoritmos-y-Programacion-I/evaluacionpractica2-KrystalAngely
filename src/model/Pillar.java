package model;

public class Pillar {

    private String name;
    private Project[] projects;

    public Pillar(String name) {
        this.name = name;
        projects = new Project[50];
    }

    public String getName() {
        return name;
    }

    /**
     * Descripcion: Añade un nuevo Proyecto al arreglo de proyectos.
     * pre: El arreglo de proyectos debe estar inicializado.
     * pos: El proyecto es añadido si hay espacio disponible.
     * 
     * @param newProject Project El proyecto que se va a añadir.
     * @return boolean true si se logra añadir el proyecto, false en caso contrario.
     */
    public boolean registerProject(Project newProject) {
        for (int i = 0; i < projects.length; i++) {
            if (projects[i] == null) {
                projects[i] = newProject;
                return true; // Proyecto añadido exitosamente
            }
        }
        return false; // No hay espacio disponible
    }

    /**
     * Descripcion: Genera una lista con la información de los proyectos registrados en el Pilar.
     * pre: El arreglo de proyectos debe estar inicializado.
     * pos: Se retorna una lista con la información de los proyectos.
     * 
     * @return String Lista de proyectos.
     */
    public String getProjectList() {
        StringBuilder list = new StringBuilder();
        boolean hasProjects = false;

        for (Project project : projects) {
            if (project != null) {
                list.append("\n").append(project.getId()).append(" - ").append(project.getName());
                hasProjects = true;
            }
        }
        if (!hasProjects) {
            return "No hay proyectos registrados en este pilar.";
        }
        return list.toString();
    }
}
