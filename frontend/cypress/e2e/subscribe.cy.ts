// https://on.cypress.io/api

describe('Subscribe home page', () => {
  it('Display the title', () => {
    cy.visit('/')
    cy.contains('h1', 'SharedGift')
  })

  /**
   * Test the subscription form nominal case with real backend
   */
  it.skip('handle subscription with real backend', () => {
    cy.visit('/')
    cy.get('input[id=name]').type('Philippe')
    cy.get('input[id=email]').type('philippe.rols@gmail.com')
    cy.get('input[id=password]').type('123456')
    cy.get('button[id=subscribe]').click()

    cy.get('p.success').should('contain', 'Votre compte a très bien été créé !')
  })

  /**
   * Test the subscription form nominal case with mock backend
   *
   */
  it('handle subscription', () => {
    cy.intercept('POST', '/api/familyadmin', {
      statusCode: 201,
      body: {
        name: 'Peter Pan',
        email: 'peter.pan@disney.org',
        password: '123456'
      },
    })
    cy.visit('/')
    cy.get('input[id=name]').type('Peter Pan')
    cy.get('input[id=email]').type('peter.pan@disney.org')
    cy.get('input[id=password]').type('123456')
    cy.get('button[id=subscribe]').click()

    cy.get('p.success').should('contain', 'Votre compte a très bien été créé !')
    cy.get('p.error').should('not.exist')
  })

  /**
   * Test the subscription form nominal case with mock backend
   *
   */
  it('handle subscription 409 error', () => {
    cy.intercept('POST', '/api/familyadmin', {
      statusCode: 409,
      body: {
        status: 409,
        error: 'Conflict',
        path: "/api/familyadmin"
      },
    })
    cy.visit('/')
    cy.get('input[id=name]').type('Peter Pan')
    cy.get('input[id=email]').type('peter.pan@disney.org')
    cy.get('input[id=password]').type('123456')
    cy.get('button[id=subscribe]').click()

    cy.get('p.success').should('not.exist')
    cy.get('p.error').should('contain', 'Un compte existe déjà avec cet email')
  })
})
