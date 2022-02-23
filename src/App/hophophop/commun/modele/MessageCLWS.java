//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MessageCLWS implements Serializable {
    public static final String CL_HALT = "CL_HALT";
    public static final String CL_CLOSE = "CL_CLOSE";
    public static final String CL_PRINTSTR = "CL_PRINTSTR";
    public static final String CL_PRINTINT = "CL_PRINTINT";
    public static final String CL_PRINTFLOAT = "CL_PRINTFLOAT";
    public static final String VALEUR = "VALEUR";
    private String type;
    private Map<String, Object> arguments;

    private MessageCLWS(String type) {
        this.type = type;
        this.arguments = new HashMap();
    }

    public Map<String, Object> getArguments() {
        return this.arguments;
    }

    public String getType() {
        return this.type;
    }

    private void addArgument(String cle, Object valeur) {
        this.arguments.put(cle, valeur);
    }

    public Object getArgument(String cle) {
        return this.arguments.get(cle);
    }

    public static MessageCLWS getMessageHaltProgram() {
        return new MessageCLWS("CL_HALT");
    }

    public static MessageCLWS getMessageCloseCodeLab() {
        return new MessageCLWS("CL_CLOSE");
    }

    public static MessageCLWS getMessagePrintStrToHop3x(String chaine) {
        MessageCLWS message = new MessageCLWS("CL_PRINTSTR");
        message.addArgument("VALEUR", chaine);
        return message;
    }

    public static MessageCLWS getMessagePrintIntToHop3x(int entier) {
        MessageCLWS message = new MessageCLWS("CL_PRINTINT");
        message.addArgument("VALEUR", entier);
        return message;
    }

    public static MessageCLWS getMessagePrintFloatToHop3x(float flottant) {
        MessageCLWS message = new MessageCLWS("CL_PRINTFLOAT");
        message.addArgument("VALEUR", flottant);
        return message;
    }
}
