package epitech.eip.smartconf.cloudstorage

import com.google.cloud.storage.StorageOptions
import com.google.cloud.storage.Acl
import com.google.cloud.storage.Acl.Role
import com.google.cloud.storage.Acl.User
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage

import java.io.ByteArrayOutputStream
import java.util.ArrayList
import java.util.Arrays

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import java.io.File

class CloudStorageHelper {
    private var storage: Storage? = null

    constructor() {
        storage = StorageOptions.getDefaultInstance().service
    }

    fun uploadFile(string: String, bucketName: String): String {
        var dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS")
        var dt = DateTime.now(DateTimeZone.UTC)
        var dtString = dt.toString(dtf)
        var fileName = string + dtString

        var iss= File(string).inputStream()
        var os = ByteArrayOutputStream()
        var readBuf = ByteArray(4096)

        while (iss.available() > 0) {
            var bytesRead = iss.read(readBuf)
            os.write(readBuf, 0, bytesRead)
        }

        var test = ArrayList(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER)))
        var blobInfo = storage?.create(BlobInfo
            .newBuilder(bucketName, fileName)
            .setAcl(test)
            .build(),
            os.toByteArray())
        return blobInfo!!.mediaLink
    }

}
