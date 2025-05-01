import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

val RepositoryHandler.mavenKff
    get() = maven("https://thedarkcolour.github.io/KotlinForForge/") {
        name = "Kotlin For Forge"
        content {
            includeGroup("thedarkcolour")
        }
    }

val RepositoryHandler.mavenBmcl
    get() = maven("https://bmclapi2.bangbang93.com/maven") {
        name = "BMCL"
    }

val RepositoryHandler.curseMaven
    get() = maven("https://www.cursemaven.com") {
        content {
            includeGroup("curse.maven")
        }
    }

val RepositoryHandler.modrinthMaven
    get() = maven("https://api.modrinth.com/maven") {
        name = "Modrinth Maven"
    }

inline fun RepositoryHandler.maven(url: String, crossinline block: MavenArtifactRepository.() -> Unit): MavenArtifactRepository = maven {
    setUrl(url)
    block()
}