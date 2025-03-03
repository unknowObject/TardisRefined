package whocraft.tardis_refined;

import com.google.common.collect.Lists;


import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.List;

public class TRConfig {

    public static Common COMMON;
    public static ModConfigSpec COMMON_SPEC;
    public static Server SERVER;
    public static ModConfigSpec SERVER_SPEC;
    public static Client CLIENT;
    public static ModConfigSpec CLIENT_SPEC;

    static {
        Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();

        Pair<Server, ModConfigSpec> specServerPair = new ModConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = specServerPair.getRight();
        SERVER = specServerPair.getLeft();


        Pair<Client, ModConfigSpec> specClientPair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specClientPair.getRight();
        CLIENT = specClientPair.getLeft();
    }



    public static class Client {
        public final ModConfigSpec.BooleanValue CONTROL_NAMES;
        public final ModConfigSpec.BooleanValue PLAY_CONSOLE_IDLE_ANIMATIONS;

        public Client(ModConfigSpec.Builder builder) {
            builder.push("rendering");
            CONTROL_NAMES = builder.comment("Toggle control name rendering").translation(ModMessages.CONFIG_CONTROL_NAMES).define("control_name_rendering", true);
            PLAY_CONSOLE_IDLE_ANIMATIONS = builder.comment("Play idle console animations").translation(ModMessages.CONFIG_IDLE_CONSOLE_ANIMS).define("console_idle_animations", true);
            builder.pop();
        }

    }

    public static class Common {
        public final ModConfigSpec.BooleanValue COMPATIBILITY_IP;

        public Common(ModConfigSpec.Builder builder) {
            builder.push("compatibility");
            COMPATIBILITY_IP = builder.comment("Toggle Immersive Portals compatibility (TR 2.0+). 2.0 has limited support and does not recommend enabling this.").translation(ModMessages.CONFIG_IP_COMPAT).define("immersive_portals_support", true);
            builder.pop();
        }

    }

    public static class Server {
        public final ModConfigSpec.ConfigValue<List<? extends String>> BANNED_DIMENSIONS;

        public Server(ModConfigSpec.Builder builder) {
            builder.push("travel");
            BANNED_DIMENSIONS = builder.translation("config.tardis_refined.banned_dimensions").comment("A list of Dimensions the TARDIS cannot land in.").defineList("banned_dimensions", Lists.newArrayList("example:dimension"), String.class::isInstance);
            builder.pop();
        }

    }


}
