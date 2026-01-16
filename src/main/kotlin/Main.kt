package v.pevlo

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import v.pevlo.util.ChatUtil.log
import v.pevlo.commands.HomeTP
import v.pevlo.commands.MineTP
import v.pevlo.commands.Reload
import v.pevlo.commands.SetHome
import v.pevlo.commands.SpawnTP
import v.pevlo.events.EventsManager
import v.pevlo.home.HomesManager
import v.pevlo.util.MessagesManager

class Main: JavaPlugin() {
    private lateinit var homesManager: HomesManager
    lateinit var msgs: MessagesManager

    companion object {
        lateinit var instance: Main
    }

    override fun onEnable() {
        saveDefaultConfig()
        instance = this
        Bukkit.getPluginManager().registerEvents(EventsManager(this), this)
        homesManager = HomesManager(this)
        msgs = MessagesManager(instance)

        getCommand("spawn")?.setExecutor(SpawnTP)
        getCommand("automine")?.setExecutor(MineTP)
        getCommand("sp")?.setExecutor(Reload)
        getCommand("sethome")?.setExecutor(SetHome)
        getCommand("home")?.setExecutor(HomeTP)

        log(msgs.get("load-message"))
    }
}