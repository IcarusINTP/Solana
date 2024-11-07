

package solana;


import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.rpc.Cluster;
import org.p2p.solanaj.rpc.RpcClient;
//import org.p2p.solanaj.rpc.RpcConfig;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.LatestBlockhash;

public class Checkbalance {
	
	
	
	
	public static long getBalance(String publickey) {
       // Connect to Solana RPC
	    	RpcClient client = new RpcClient(Cluster.MAINNET);

	    	try {
			 
				long balance = client.getApi().getBalance(new PublicKey(publickey));
				return balance;
			} catch (RpcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	return -1;

}
}