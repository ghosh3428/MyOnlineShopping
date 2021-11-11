<%@include file="../shared-flow/flow-header.jsp"%>
<div class="container">

	<div class="row">

		<div class="col-md-6">

			<div class="card">

				<div class="card-heading bg-primary text-white">
					<h4>Personal Details</h4>
				</div>

				<div class="card-body">
					<div class="text-center">
						<h3>
							Name : <strong>Amit Kumar</strong>
						</h3>
						<h4>
							Email : <strong>am@gmail.com</strong>
						</h4>
						<h4>
							Contact : <strong>1234567890</strong>
						</h4>
						<h4>
							Role : <strong>USER</strong>
						</h4>
						<p>
							<a href="${flowExecutionUrl}&_eventId_personal"
								class="btn btn-primary">Edit</a>
						</p>
					</div>
				</div>

			</div>


		</div>

		<div class="col-md-6">

			<div class="card">

				<div class="card-heading bg-primary text-white">
					<h4>Billing Address</h4>
				</div>

				<div class="card-body">
					<div class="text-center">
						<p><strong>Flat N0. 2</strong>,</p>
						<p><strong>Abc Appartment</strong>,</p>
						<p><strong>Kolkata</strong>-
							<strong>700065</strong>,</p>
						<p><strong>West Bengal</strong></p>
						<p><strong>India</strong></p>
						<p>
							<a href="${flowExecutionUrl}&_eventId_billing"
								class="btn btn-primary">Edit</a>
						</p>
					</div>
				</div>

			</div>

		</div>

	</div>
	<br />
	<div class="row">

		<div class="offset-md-5 col-md-2">

			<div class="text-center">

				<a href="${flowExecutionUrl}&_eventId_success"
					class="btn btn-lg btn-primary">Confirm</a>

			</div>

		</div>

	</div>

</div>
<%@include file="../shared-flow/flow-footer.jsp"%>