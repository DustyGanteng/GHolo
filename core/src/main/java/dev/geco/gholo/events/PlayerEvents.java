package dev.geco.gholo.events;

import org.bukkit.event.*;
import org.bukkit.event.player.*;

import dev.geco.gholo.GHoloMain;

public class PlayerEvents implements Listener {

    private final GHoloMain GPM;

    public PlayerEvents(GHoloMain GPluginMain) { GPM = GPluginMain; }

    @EventHandler
    public void PJoiE(PlayerJoinEvent Event) { GPM.getUManager().loginCheckForUpdates(Event.getPlayer()); }

}