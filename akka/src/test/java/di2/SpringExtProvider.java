package di2;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;

/**
 * @author lxq
 * @create 2019/12/15 22:54
 */
public class SpringExtProvider extends AbstractExtensionId<SpringExt> {
    private static SpringExtProvider provider = new SpringExtProvider();

    public static SpringExtProvider getInstance() {
        return provider;
    }

    @Override
    public SpringExt createExtension(ExtendedActorSystem extendedActorSystem) {
        return new SpringExt();
    }
}
