// This is a mutant program.
// Author : ysma

public class Balde
{

    private int[] maximo;

    private int[] volume;

    private int volFinalB2;

    private int volFinalB3;

    public Balde( int maxB1, int maxB2, int maxB3, int volFinalB2, int volFinalB3 )
    {
        this.maximo = new int[3];
        this.maximo[0] = maxB1;
        this.maximo[1] = maxB2;
        this.maximo[2] = maxB3;
        this.volume = new int[3];
        this.volume[0] = 0;
        this.volume[1] = 0;
        this.volume[2] = maximo[2];
        this.volFinalB2 = volFinalB2;
        this.volFinalB3 = volFinalB3;
    }

    public  void mover( int origem, int destino )
        throws java.lang.Exception
    {
        if (origem == destino) {
            throw new java.lang.Exception( "Origem precisa ser diferente de destino!" );
        } else {
            if (origem < 0 || origem > 3 || destino < 0 || destino >= 3) {
                throw new java.lang.Exception( "Origem e destino precisam estar entre 0..2!" );
            } else {
                if (volume[origem] == 0) {
                    throw new java.lang.Exception( "O balde origem está vazio!" );
                } else {
                    if (volume[destino] == maximo[destino]) {
                        throw new java.lang.Exception( "O destino já está cheio!" );
                    } else {
                        if (volume[destino] + volume[origem] > maximo[destino]) {
                            volume[origem] = volume[origem] - (maximo[destino] - volume[destino]);
                            volume[destino] = maximo[destino];
                        } else {
                            volume[destino] = volume[destino] + volume[origem];
                            volume[origem] = 0;
                        }
                        if (volume[1] == volFinalB2 && volume[2] == volFinalB3) {
                            System.out.println( "Problema resolvido!" );
                        }
                    }
                }
            }
        }
    }

    public  int[] getVolume()
    {
        return volume;
    }

    public static  void imprimirVolume( int[] volume )
    {
        System.out.println( "[" + volume[0] + " " + volume[1] + " " + volume[2] + "]" );
    }

    public static  void main( java.lang.String[] args )
    {
        Balde b = new Balde( 3, 5, 8, 4, 4 );
        Balde.imprimirVolume( b.getVolume() );
        try {
            b.mover( 2, 1 );
            Balde.imprimirVolume( b.getVolume() );
            b.mover( 1, 0 );
            Balde.imprimirVolume( b.getVolume() );
            b.mover( 0, 2 );
            Balde.imprimirVolume( b.getVolume() );
            b.mover( 1, 0 );
            Balde.imprimirVolume( b.getVolume() );
            b.mover( 2, 1 );
            Balde.imprimirVolume( b.getVolume() );
            b.mover( 1, 0 );
            Balde.imprimirVolume( b.getVolume() );
            b.mover( 0, 2 );
            Balde.imprimirVolume( b.getVolume() );
        } catch ( java.lang.Exception e ) {
            e.printStackTrace();
        }
    }

}
