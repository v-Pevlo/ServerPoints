package v.pevlo.home

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import v.pevlo.Main
import v.pevlo.util.ChatUtil.log
import java.io.File

private val plugin = Main.instance
private val msgs = plugin.msgs

class HomesManager(private val plugin: JavaPlugin) {
    private val folder = File(plugin.dataFolder, "homes")
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    init {
        if (!folder.exists()) folder.mkdirs()
    }

    fun saveHome(home: Home) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable {
            val file = File(folder, "${home.uuid}.json")
            val content = json.encodeToString(home)
            file.writeText(content)
            log(
                msgs.get("new-home-log"),
                Pair("%uuid%", home.uuid),
                Pair("%x%", "%.2f".format(home.loc.x)),
                Pair("%y%", "%.2f".format(home.loc.y)),
                Pair("%z%","%.2f".format(home.loc.z))
                )
        })
    }

    fun loadHome(uuid: String): Home? {
        val file = File(folder, "$uuid.json")
        if (!file.exists()) return null

        return try {
            json.decodeFromString<Home>(file.readText())
        } catch (e: Exception) {
            log("&9$uuid &eData loading error: &c${e.message}")
            null
        }
    }
}

@Serializable
data class Home(
    val uuid: String,
    val loc: SerializableLocation
)

@Serializable
data class SerializableLocation(
    var world: String,
    var x: Double,
    var y: Double,
    var z: Double,
    var yaw: Float = 0f,
    var pitch: Float = 0f
) {
    fun toBukkit(): Location? {
        val worldObj = Bukkit.getWorld(world) ?: return null
        return Location(worldObj, x, y, z, yaw, pitch)
    }

    companion object {
        fun fromBukkit(loc: Location) = SerializableLocation(
            loc.world?.name ?: "world",
            loc.x, loc.y, loc.z, loc.yaw, loc.pitch
        )
    }

}
