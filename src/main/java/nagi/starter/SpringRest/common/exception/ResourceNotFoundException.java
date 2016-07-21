package nagi.starter.SpringRest.common.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
//@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "Forbidden Error!!!!")
public class ResourceNotFoundException extends RuntimeException {

	private Long id;

	public ResourceNotFoundException() {
	}

	public ResourceNotFoundException(Long id) {
		this.id = id;
	}

	public Long getResourceId() {
		return id;
	}
}
