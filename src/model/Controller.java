package model;

public class Controller {

    private Pillar[] pillars;

    public Controller() {
        pillars = new Pillar[4];
        // Pre-cargar pilares
        pillars[0] = new Pillar("BIODIVERSIDAD");
        pillars[1] = new Pillar("AGUA");
        pillars[2] = new Pillar("TRATAMIENTO DE BASURAS");
        pillars[3] = new Pillar("ENERGIA");
    }

    /**
     * Descripcion: Permite crear y añadir un Proyecto en un Pilar en el sistema.
     * pre: El arreglo de pilares debe estar inicializado.
     * pos: El proyecto es registrado en el pilar correspondiente.
     * 
     * @param id String ID del proyecto.
     * @param name String Nombre del proyecto.
     * @param description String Descripción del proyecto.
     * @param type int Número del tipo de pilar.
     * @param status boolean Estado del proyecto.
     * @return boolean true si el proyecto es registrado exitosamente, false en caso contrario.
     */
    public boolean registerProjectInPillar(String id, String name, String description, int type, boolean status) {
        PillarType pillarType = null;
        switch (type) {
            case 1:
                pillarType = PillarType.BIODIVERSIDAD;
                break;
            case 2:
                pillarType = PillarType.AGUA;
                break;
            case 3:
                pillarType = PillarType.TRATAMIENTO_DE_BASURAS;
                break;
            case 4:
                pillarType = PillarType.ENERGIA;
                break;
            default:
                return false; // Tipo inválido
        }

        Project newProject = new Project(id, name, description, status, pillarType);
        return pillars[type - 1].registerProject(newProject); // Registrar proyecto en el pilar correspondiente
    }

    /**
     * Descripcion: Consulta los proyectos registrados en un pilar.
     * pre: El sistema debe estar cargado con al menos un proyecto.
     * pos: Retorna la lista de proyectos registrados en el pilar solicitado.
     * 
     * @param pillarName String Nombre del pilar.
     * @return String Lista de proyectos en el pilar o null si no hay proyectos.
     */
    public String queryProjectsByPillar(String pillarName) {
        for (Pillar pillar : pillars) {
            if (pillar != null && pillar.getName().equalsIgnoreCase(pillarName)) {
                return pillar.getProjectList();
            }
        }
        return null;
    }

    /**
     * Descripcion: Retorna una lista de todos los pilares disponibles.
     * pre: El sistema debe estar inicializado.
     * pos: Se retorna la lista de pilares.
     * 
     * @return String Lista de pilares.
     */
    public String getPillarList() {
        StringBuilder list = new StringBuilder("Pilares disponibles:");
        for (Pillar pillar : pillars) {
            if (pillar != null) {
                list.append("\n- ").append(pillar.getName());
            }
        }
        return list.toString();
    }
}
