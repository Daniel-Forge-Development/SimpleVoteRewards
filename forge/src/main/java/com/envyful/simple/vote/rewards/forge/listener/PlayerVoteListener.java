package com.envyful.simple.vote.rewards.forge.listener;

import com.envyful.api.forge.player.util.UtilPlayer;
import com.envyful.api.forge.server.UtilForgeServer;
import com.envyful.simple.vote.rewards.forge.SimpleVoteRewardsForge;
import com.vexsoftware.votifier.sponge.event.VotifierEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.event.Listener;

public class PlayerVoteListener {

    private final SimpleVoteRewardsForge mod;

    public PlayerVoteListener(SimpleVoteRewardsForge mod) {
        this.mod = mod;
    }

    @Listener
    public void onPlayerVote(VotifierEvent event) {
        EntityPlayerMP player = UtilPlayer.findByName(event.getVote().getUsername());

        if (player == null) {
            return;
        }

        for (String rewardCommand : this.mod.getConfig().getRewardCommands()) {
            UtilForgeServer.executeCommand(rewardCommand.replace("%player%", player.getName()));
        }
    }
}
