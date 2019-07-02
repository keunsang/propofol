package bean;

public class Forward {
	// false:dispatcher 포워딩
	// true :redirect 포워딩
	private boolean isRedirect = false;
	// 포워딩 할 뷰페이지 또는 url
	private String path; // null

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
