/**
 * © Nowina Solutions, 2015-2015
 *
 * Concédée sous licence EUPL, version 1.1 ou – dès leur approbation par la Commission européenne - versions ultérieures de l’EUPL (la «Licence»).
 * Vous ne pouvez utiliser la présente œuvre que conformément à la Licence.
 * Vous pouvez obtenir une copie de la Licence à l’adresse suivante:
 *
 * http://ec.europa.eu/idabc/eupl5
 *
 * Sauf obligation légale ou contractuelle écrite, le logiciel distribué sous la Licence est distribué «en l’état»,
 * SANS GARANTIES OU CONDITIONS QUELLES QU’ELLES SOIENT, expresses ou implicites.
 * Consultez la Licence pour les autorisations et les restrictions linguistiques spécifiques relevant de la Licence.
 */
package lu.nowina.nexu.api;

import java.util.List;

import eu.europa.esig.dss.DigestAlgorithm;
import eu.europa.esig.dss.token.DSSPrivateKeyEntry;
import eu.europa.esig.dss.token.PasswordInputCallback;
import eu.europa.esig.dss.token.SignatureTokenConnection;

/**
 * A CardAdapter can open connection for specific smartcard.
 * 
 * @author David Naramski
 *
 */
public interface CardAdapter {

	/**
	 * Query the CardAdapter to know if he is able to connect the DetectedCard
	 * 
	 * @param card
	 * @return true if the CardAdapter can open a connection to the DetectedCard
	 */
	boolean accept(DetectedCard card);

	/**
	 * Open a connection the the SmartCard
	 * 
	 * @param api
	 * @param card
	 * @param callback
	 * @return
	 */
	SignatureTokenConnection connect(NexuAPI api, DetectedCard card, PasswordInputCallback callback);

	/**
	 * Returns <code>true</code> if this card adapter can return identity information for the given <code>card</code>.
	 * @return <code>true</code> if this card adapter can return identity information for the given <code>card</code>.
	 */
	boolean canReturnIdentityInfo(DetectedCard card);
	
	/**
	 * Returns the identity information using the given <code>token</code>.
	 * @param token The token to use to get the identity information.
	 * @return The identity information using the given <code>token</code>.
	 */
	GetIdentityInfoResponse getIdentityInfo(SignatureTokenConnection token);
	
	/**
	 * Returns <code>true</code> if this card adapter supports {@link CertificateFilter} for the given <code>card</code>.
	 * @return <code>true</code> if this card adapter supports {@link CertificateFilter} for the given <code>card</code>.
	 */
	boolean supportCertificateFilter(DetectedCard card);
	
	/**
	 * Returns the keys of <code>token</code> matching the <code>certificateFilter</code>.
	 * @param token The token to use to retrieve the keys.
	 * @param certificateFilter Filter that must be matched by returned keys.
	 * @return The keys of <code>token</code> matching the <code>certificateFilter</code>.
	 */
	List<DSSPrivateKeyEntry> getKeys(SignatureTokenConnection token, CertificateFilter certificateFilter);
	
	/**
	 * Returns <code>true</code> if this card adapter can return supported digest algorithms for the given <code>card</code>.
	 * @param card The card for which one would like to retrieve the supported digest algorithms.
	 * @return <code>true</code> if this card adapter can return supported digest algorithms for the given <code>card</code>.
	 */
	boolean canReturnSuportedDigestAlgorithms(DetectedCard card);
	
	/**
	 * Returns the list of supported digest algorithms for the given <code>card</code>.
	 * @param card The card for which one would like to retrieve the supported digest algorithms.
	 * @return The list of supported digest algorithms for the given <code>card</code>.
	 */
	List<DigestAlgorithm> getSupportedDigestAlgorithms(DetectedCard card);
}
