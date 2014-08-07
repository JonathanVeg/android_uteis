package wexpense.wallet.abastract;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public class BasicNotification {

	private Context context;

	private NotificationManager notificationManager;

	private NotificationCompat.Builder builder;

	private Notification notification;

	public BasicNotification(Context c) {
		this.context = c;
	}

	public void createBasicNotification(final int icon, final String title,
			final String text) {
		
		builder = new NotificationCompat.Builder(context);
		notification = builder.setSmallIcon(icon).setContentTitle(title)
				.setContentText(text).build();

		notification.tickerText = "Upando sua imagem";

		notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(1, notification);
	}

	public NotificationManager getNotificationManager() {
		return notificationManager;
	}

	public Notification getNotification() {
		return notification;
	}

	public void cancel() {
		if (notificationManager != null) {
			notificationManager.cancel(1);
		}
	}

}
