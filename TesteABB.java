package TesteABB;

public class TesteABB {

	public static void main(String[] args) {
        System.out.println("\n\nCriação de uma ABB com objetos Integer:\n");
        ABB<Integer> abb1 = new ABB<Integer>();
        System.out.println( "Insere " + abb1.insert(12) );
        System.out.println( "Insere " + abb1.insert(6) );
        System.out.println( "Insere " + abb1.insert(4) );
        System.out.println( "Insere " + abb1.insert(15) );
        System.out.println( "Insere " + abb1.insert(13) );
        System.out.println( "Insere " + abb1.insert(25) );
        System.out.println( "Insere " + abb1.insert(2) );
        System.out.println( "Insere " + abb1.insert(5) );
        
        System.out.println("\n\nMostra a ABB percorrendo em-ordem:\n");
        System.out.println( abb1.emOrdem() );
        System.out.println("\nMostra a ABB percorrendo pré-ordem:\n");
        abb1.preOrdem();
        System.out.println("\nMostra a ABB percorrendo pós-ordem:\n");
        abb1.posOrdem();
        System.out.println("\nMostra a ABB percorrendo em nível:\n");
        abb1.emNivel();

        System.out.println("\n\nCriação de uma ABB de objetos da classe Aluno (Chave: RA):\n");
        ABB<Aluno> abb2 = new ABB<Aluno>();
         
        // Criação dos alunos
        Aluno alA = new Aluno("888-8", "Caio", 'M', 5.5f);
        Aluno alB = new Aluno("333-3", "Lara", 'F', 9.8f);
        Aluno alC = new Aluno("666-6", "Vanessa", 'F', 8.8f);
        Aluno alD = new Aluno("111-1", "Luiz", 'M', 6.5f);
        Aluno alE = new Aluno("999-9", "Ana", 'F', 9.5f);
        
        // Insere os alunos na árvore
        // A chave da ABB é RA do Aluno
        abb2.inserir(new Aluno("123-4", "Rosa", 'F', 4.5f));
        abb2.inserir(alE);
        abb2.inserir(alD);
        abb2.inserir(new Aluno("444-4", "Betty", 'F', 9.0f));
        abb2.inserir(new Aluno("222-2", "Pedro", 'M', 7.0f));
        abb2.inserir(new Aluno("777-7", "Renata", 'F', 7.2f));
        abb2.inserir(alA);
        abb2.inserir(alB);
        abb2.inserir(alC);
        
        // Imprime os alunos em ordem de RA
        abb2.emOrdem2();  
       
        // Se conseguiu eliminar o aluno de RA 666-6
        if (abb2.eliminar(alC) == true) 
        	System.out.println("\nEliminado: " + alC);
        else 
        	System.out.println("\nNão eliminado: " + alC);
        
        // Busca os alunos na árvore e mostra, se existir
        System.out.println("\nBuscamos " + alA +   ", Resultado: " + abb2.find(alA));
        System.out.println("\nBuscamos " + alC +   ", Resultado: " + abb2.find(alC));
        System.out.println("\nBuscamos " + alB +   ", Resultado: " + abb2.search(alB) + "\n");
        
        // Imprime o resultado da árvore em ordem de RA
        System.out.println("\nOs alunos que estão na árvore por RA são:");
        abb2.emOrdem2();      
	}
}
