package mx.com.liverpool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@RequestMapping("/gcp")
@RestController
public class StorageController {

	@Autowired
	private Storage storage;

	@GetMapping("/upload")
	public ResponseEntity<Object> subirArchivo() throws IOException {
		BlobId id = BlobId.of("ejemplo-bucket-01234", "demo2.txt");
		BlobInfo info = BlobInfo.newBuilder(id).build();
		File file =new ClassPathResource("demo.txt").getFile();
		byte[] arr = Files.readAllBytes(Paths.get(file.toURI()));
		storage.create(info, arr);
		return new ResponseEntity<Object>("Información enviada", HttpStatus.OK);
	}
}
