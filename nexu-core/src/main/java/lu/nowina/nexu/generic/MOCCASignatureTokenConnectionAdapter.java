package lu.nowina.nexu.generic;

import java.util.List;

import at.gv.egiz.smcc.CancelledException;
import at.gv.egiz.smcc.TimeoutException;
import eu.europa.esig.dss.DSSException;
import eu.europa.esig.dss.DigestAlgorithm;
import eu.europa.esig.dss.SignatureValue;
import eu.europa.esig.dss.ToBeSigned;
import eu.europa.esig.dss.token.DSSPrivateKeyEntry;
import eu.europa.esig.dss.token.SignatureTokenConnection;
import lu.nowina.nexu.CancelledOperationException;

/**
 * This adapter class allows to manage {@link CancelledOperationException}.
 *
 * @author Jean Lepropre (jean.lepropre@nowina.lu)
 */
public class MOCCASignatureTokenConnectionAdapter implements SignatureTokenConnection {
	
	private SignatureTokenConnection adapted;

	public MOCCASignatureTokenConnectionAdapter(SignatureTokenConnection adapted) {
		super();
		this.adapted = adapted;
	}

	public void close() {
		adapted.close();
	}

	public List<DSSPrivateKeyEntry> getKeys() throws DSSException {
		try {
			return adapted.getKeys();
		} catch(final Exception e) {
			Throwable t = e;
			while(t != null) {
				if((t instanceof CancelledException) ||
						(t instanceof TimeoutException)) {
					throw new CancelledOperationException(e);
				}
				t = t.getCause();
			}
			// Rethrow exception as is.
			throw e;
		}
	}

	public SignatureValue sign(ToBeSigned toBeSigned, DigestAlgorithm digestAlgorithm, DSSPrivateKeyEntry keyEntry) throws DSSException {
		try {
			return adapted.sign(toBeSigned, digestAlgorithm, keyEntry);
		} catch(final Exception e) {
			Throwable t = e;
			while(t != null) {
				if((t instanceof CancelledException) ||
						(t instanceof TimeoutException)) {
					throw new CancelledOperationException(e);
				}
				t = t.getCause();
			}
			// Rethrow exception as is.
			throw e;
		}
	}
}