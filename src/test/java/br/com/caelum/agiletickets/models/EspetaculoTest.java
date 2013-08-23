package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.validator.AssertTrue;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void comecaETerminaNoMesmoDiaPeriodicidadeDiariaCria1SessaoCorreta() {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate hoje = new LocalDate(2013,10,10);
		LocalTime horario = new LocalTime(20,30);
		List<Sessao> sessoes = espetaculo.criaSessoes(hoje, hoje, horario, Periodicidade.DIARIA);
		Assert.assertEquals(1, sessoes.size());
	}
	
	@Test
	public void criamSessoesQueApontamParaOEspetaculoEInicioCorreto() {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setId(1L);
		LocalDate hoje = new LocalDate(2013,10,10);
		LocalTime horario = new LocalTime(20,30);
		List<Sessao> sessoes = espetaculo.criaSessoes(hoje, hoje, horario, Periodicidade.DIARIA);
		Sessao sessaoCriada = sessoes.get(0);
		Assert.assertEquals(espetaculo.getId(),sessaoCriada.getEspetaculo().getId());
	}
	
	@Test
	public void comecaETerminaNoMesmoDiaPeriodicidadeSemanalCria1SessaoCorreta() {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.criaSessoes(new LocalDate(2013,10,10), new LocalDate(2013,10,10), new LocalTime(20,30), Periodicidade.SEMANAL);
		Assert.assertEquals(1, espetaculo.getSessoes().size());
	}
	
	@Test
	public void comecaHojeETerminaAmanhaPeriodicidadeDiariaCria2SessoesCorretas() {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.criaSessoes(new LocalDate(2013,10,10), new LocalDate(2013,10,11), new LocalTime(20,30), Periodicidade.DIARIA);
		Assert.assertEquals(2, espetaculo.getSessoes().size());
	}

	@Test
	public void comecaHojeETerminaAmanhaPeriodicidadeSemanalCria1SessaoCorreta() {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.criaSessoes(new LocalDate(2013,10,10), new LocalDate(2013,10,11), new LocalTime(20,30), Periodicidade.SEMANAL);
		Assert.assertEquals(1, espetaculo.getSessoes().size());
	}
}
