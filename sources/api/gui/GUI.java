public class GUI
{
    private static GUI instance = null;

    private GUI()
    {
        System.out.println("GUI class created!");
    }

    public static GUI init()
    {
        if(instance == null)
            instance = new GUI();
        return(instance);
    }
}
