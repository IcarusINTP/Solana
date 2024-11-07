package solana;

	
	
	import org.bitcoinj.core.Base58;
import org.p2p.solanaj.core.Account;
	import org.p2p.solanaj.core.PublicKey;
	import org.p2p.solanaj.core.Transaction;
import org.p2p.solanaj.core.TransactionInstruction;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.rpc.Cluster;
	import org.p2p.solanaj.rpc.RpcClient;
	import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.LatestBlockhash;


public class Transfer {
	
	
	public static void main(String Args[])
	{RpcClient client = new RpcClient( Cluster.MAINNET);


	PublicKey fromPublicKey = new PublicKey("7nLGLhxrGeeNhxKf1fKdXsEcuzG55bqQ6vmyur7hVCsg");
	PublicKey recipient = new PublicKey("HZUSokDyWjqiduPrWB3J5k7Bhu3DpXinZVzvb8ZhvvhT");
	int amount = 2000000;

	  String base58PrivateKey = "21efag2JC4AZFJyAqnnrauCZA6KoAhrv7d5HYo5CPNmnuEgAmWxM4ZE1Uc9oPzp9j66uJ9LsPL9Pi1RZ2d5aD2NJ";
      
      // Decode the base58 private key
      byte[] privateKey = Base58.decode(base58PrivateKey);
      
      // Initialize the account with the private key
      Account sender = new Account(privateKey);

      try {
          // Fetch recent blockhash
          LatestBlockhash recentBlockhash = client.getApi().getLatestBlockhash(); // Adjust as per your SDK response
String a = recentBlockhash.getValue().toString();
          // Create a new transaction
          Transaction transaction = new Transaction();
          transaction.setRecentBlockHash(a); // Set the recent blockhash

          // Create transfer instruction
          TransactionInstruction transferInstruction = SystemProgram.transfer(sender.getPublicKey(), recipient, amount);
          transaction.addInstruction(transferInstruction);

          // Sign the transaction
          transaction.sign(sender);

          // Send the transaction
          String signature = client.getApi().sendTransaction(transaction, sender);
          System.out.println("Transaction sent with signature: " + signature);

      } catch (RpcException e) {
          System.err.println("RPC error: " + e.getMessage());
          e.printStackTrace();
      } catch (Exception e) {
          System.err.println("General error: " + e.getMessage());
          e.printStackTrace();
      }
  }
	  /* public static void main(String[] args) {
	        // Initialize the client to connect to Solana devnet
	        RpcClient client = new RpcClient(Cluster.MAINNET);
	       
	        
	        String base58PrivateKey = "21efag2JC4AZFJyAqnnrauCZA6KoAhrv7d5HYo5CPNmnuEgAmWxM4ZE1Uc9oPzp9j66uJ9LsPL9Pi1RZ2d5aD2NJ";
	        
	        // Decode the base58 private key
	        byte[] privateKey = Base58.decode(base58PrivateKey);
	        
	        // Initialize the account with the private key
	        Account sender = new Account(privateKey);
	        // Sender account (this should be an existing account with sufficient balance)
	         // Replace with your sender's private key
	        PublicKey recipient = new PublicKey("HZUSokDyWjqiduPrWB3J5k7Bhu3DpXinZVzvb8ZhvvhT"); // Recipient's address

	        // Define the transaction amount in lamports (1 SOL = 1 billion lamports)
	        long amount = 2000000; // e.g., 0.001 SOL
	        try {
	            // Fetch recent blockhash
	            RpcResponse<String> response = client.getApi().getRecentBlockhash();
	            String recentBlockhash = response.getResult();

	            // Create a new transaction with the recent blockhash
	            Transaction transaction = new Transaction(recentBlockhash);

	            // Create transfer instruction
	            TransactionInstruction transferInstruction = SystemProgram.transfer(sender.getPublicKey(), recipient, amount);
	            transaction.addInstruction(transferInstruction);

	            // Sign the transaction
	            transaction.sign(sender);

	            // Send the transaction
	            String signature = client.getApi().sendTransaction(transaction, sender);
	            System.out.println("Transaction sent with signature: " + signature);

	        } catch (RpcException e) {
	            e.printStackTrace();
	        }
	    }*/
	}


