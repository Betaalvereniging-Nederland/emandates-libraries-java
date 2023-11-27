package net.emandates.merchant.library.release;

/**
 * ReleaseInfo class, to be used for retrieving library version information
 */
public final class ReleaseInfo {
	private static ReleaseInfo instance = null;
	private final String version;

	private ReleaseInfo() {
		version = this.getClass()
					  .getPackage()
					  .getImplementationVersion();
	}

	/**
	 * Get the ReleaseInfo instance
	 *
	 * @return the ReleaseInfo instance
	 */
	public static ReleaseInfo getReleaseInfo() {
		if(instance != null) {
			return instance;
		}
		synchronized(ReleaseInfo.class) {
			if(instance != null) {
				return instance;
			}
			instance = new ReleaseInfo();
			return instance;
		}
	}

	/**
	 * Get version of the library
	 *
	 * @return the version of the library
	 */
	public String getVersion() {
		return version;
	}
}
