package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void varastonArvoOnNollaJosVääräArvo() {
        Varasto varasto2 = new Varasto(-2);
        assertEquals(0.0, varasto2.getTilavuus(), vertailuTarkkuus);
        
    }
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void lisaysVarastoonVaarillaArvoilla() {
        varasto.lisaaVarastoon(-5);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void otaVarastonVaarienArvojenTestaus() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-5);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        varasto.otaVarastosta(10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void merkkijonoesitysToimii() {
        String varastoString = "saldo = 0.0, vielä tilaa 10.0";
        assertEquals(varasto.toString(), varastoString);
    }
    @Test
    public void varastoMissaAlkuSaldoMyosToimii() {
        Varasto varastoAlku = new Varasto(15, 5);
        assertEquals(15, varastoAlku.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varastoAlku.getSaldo(), vertailuTarkkuus);
        Varasto varastoAlkuVäärin = new Varasto(-3, -5);
        assertEquals(0, varastoAlkuVäärin.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varastoAlkuVäärin.getSaldo(), vertailuTarkkuus);
        Varasto varastoAlkuTäynnä = new Varasto (7, 12);
        assertEquals(6, varastoAlkuTäynnä.getTilavuus(), vertailuTarkkuus);
        assertEquals(7, varastoAlkuTäynnä.getSaldo(), vertailuTarkkuus);
        
    }
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}