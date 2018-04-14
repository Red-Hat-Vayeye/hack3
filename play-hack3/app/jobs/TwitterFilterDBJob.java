import play.jobs.*;

@OnApplicationStart
@Every("10mn")
public class TwitterStreamDBJob extends Job {

    public void doJob() {
        List<User> newUsers = User.find("newAccount = true").fetch();
        for(User user : newUsers) {
            Notifier.sayWelcome(user);
        }
    }
}
